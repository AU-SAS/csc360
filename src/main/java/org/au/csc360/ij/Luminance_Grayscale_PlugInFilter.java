package org.au.csc360.ij;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;

import java.io.File;
import java.nio.file.Path;

/**
 * An example of {@link PlugInFilter}, the interface for a plugin that transforms an image already
 * open, contrasted here with {@link Ramp_Image_PlugIn}, which creates one.
 *
 * <p>The division of labour between the interface's two methods is the substance of the example.
 * {@link PlugInFilter#setup(String, ImagePlus)} is a negotiation conducted before any pixel is
 * touched: the filter returns a set of capability flags, and ImageJ uses them to decide whether the
 * filter may run on the image at hand. This example returns {@code DOES_RGB}, declaring that it
 * accepts 24-bit colour and nothing else, whereupon ImageJ declines to apply it to an 8-bit or
 * 16-bit image instead of allowing it to misread the pixel layout. The flags also govern behaviour
 * the filter never implements itself -- {@code NO_CHANGES} suppresses the undo snapshot,
 * {@code SUPPORTS_MASKING} restricts the work to a selection, and returning {@code DONE} abandons
 * the operation -- so a great deal of what a filter does is declared rather than coded.
 *
 * <p>{@link PlugInFilter#run(ImageProcessor)} then receives the {@link ImageProcessor} alone and
 * not the {@link ImagePlus}, a signature that states the contract: the filter operates on pixels in
 * place, and the framework retains responsibility for the window, the undo buffer and, where the
 * image is a stack, for calling the filter once per slice.
 *
 * <p>The transformation itself is the conversion of colour to gray by luminance. The three channels
 * are weighted 0.2126, 0.7152 and 0.0722 for red, green and blue, the Rec. 709 coefficients, rather
 * than averaged in equal thirds. The weights approximate the sensitivity of human vision, which is
 * far greater to green than to blue; an unweighted mean renders a saturated blue and a saturated
 * green as a similar mid-gray, whereas the weighted sum preserves the difference in apparent
 * brightness that an observer reports. The distinction is visible in the colour bands of the test
 * image this example loads.
 *
 * <p>One consequence of operating on a {@link ColorProcessor} is worth recording. Writing the computed
 * luminance into all three channels produces an image that appears gray while remaining 24-bit RGB,
 * at three bytes per pixel for one byte of information. Converting the type, rather than equalising
 * the channels, is a separate operation and belongs to a different filter.
 */
public class Luminance_Grayscale_PlugInFilter implements PlugInFilter {

    private static final double RED_WEIGHT = 0.2126;
    private static final double GREEN_WEIGHT = 0.7152;
    private static final double BLUE_WEIGHT = 0.0722;

    @Override
    public int setup(String arg, ImagePlus imp) {
        return DOES_RGB;
    }

    @Override
    public void run(ImageProcessor ip) {
        int width = ip.getWidth();
        int height = ip.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int packed = ip.get(x, y);
                int red = (packed >> 16) & 0xFF;
                int green = (packed >> 8) & 0xFF;
                int blue = packed & 0xFF;

                int luminance = (int) Math.round(
                        RED_WEIGHT * red + GREEN_WEIGHT * green + BLUE_WEIGHT * blue);

                // The same value in all three channels: gray in appearance, RGB in type.
                ip.set(x, y, (luminance << 16) | (luminance << 8) | luminance);
            }
        }
    }

    /**
     * Applies the filter to the image written by {@code org.au.csc360.gfx.SaveDrawingAsPngAndJpeg},
     * falling back to a generated colour image where that file is absent, so that the example
     * remains runnable on its own.
     */
    public static void main(String[] args) {
        ImagePlus image = loadTestImage();
        image.setTitle(image.getTitle() + " - luminance gray");

        Luminance_Grayscale_PlugInFilter filter = new Luminance_Grayscale_PlugInFilter();
        int flags = filter.setup("", image);
        if ((flags & DOES_RGB) == 0) {
            IJ.log("The filter declined the image type");
            return;
        }

        filter.run(image.getProcessor());
        image.show();
    }

    private static ImagePlus loadTestImage() {
        File saved = Path.of("target", "demo-output", "drawing.png").toFile();
        if (saved.isFile()) {
            ImagePlus opened = IJ.openImage(saved.getAbsolutePath());
            if (opened != null && opened.getProcessor() instanceof ColorProcessor) {
                return opened;
            }
        }
        return syntheticColourBands();
    }

    /** Six saturated bands, the case in which weighted and unweighted conversion differ most. */
    private static ImagePlus syntheticColourBands() {
        int width = 600;
        int height = 200;
        ColorProcessor ip = new ColorProcessor(width, height);
        int[] bands = {0xFF0000, 0x00FF00, 0x0000FF, 0xFFFF00, 0x00FFFF, 0xFF00FF};
        int bandWidth = width / bands.length;

        for (int x = 0; x < width; x++) {
            int colour = bands[Math.min(x / bandWidth, bands.length - 1)];
            for (int y = 0; y < height; y++) {
                ip.set(x, y, colour);
            }
        }
        return new ImagePlus("Colour bands", ip);
    }
}
