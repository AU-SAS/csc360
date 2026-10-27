package org.au.csc360.ij;

import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;

/**
 * An example of {@link PlugIn}, the simpler of ImageJ's two plugin interfaces.
 *
 * <p>A {@code PlugIn} has a single method, {@link PlugIn#run(String)}, and receives no image. It is
 * the interface for work that does not begin with pixels already on screen: creating an image,
 * reading one from a device or a database, launching a dialog, or driving a batch of other
 * commands. This example generates a horizontal intensity ramp, an 8-bit image whose value rises
 * linearly from 0 at the left edge to 255 at the right.
 *
 * <p>The ramp is the standard test pattern for the display end of the pipeline. Because its
 * intensity is known to be linear by construction, any banding, stepping or color cast visible in
 * it belongs to the display or to the lookup table rather than to the data, which makes it a
 * control against which other images can be judged.
 *
 * <p>Two details of the construction are worth notice. An {@link ImageProcessor} holds the pixels
 * and the operations on them, while the {@link ImagePlus} wraps a processor together with the
 * title, calibration and slice information that ImageJ's interface requires; a plugin that creates
 * an image therefore builds the processor first and the {@code ImagePlus} around it. The subclass
 * chosen, {@link ByteProcessor}, fixes the image as 8-bit grayscale, which is why the ramp occupies
 * exactly the 0 to 255 range that a single byte per pixel affords.
 *
 * <p><strong>On discovery and the underscore.</strong> ImageJ populates its Plugins menu by
 * scanning the directory named by the {@code plugins.dir} property, and it lists only those classes
 * whose names contain an underscore, which is rendered as a space in the menu entry. A plugin named
 * {@code RampImage} is therefore invisible however correctly it is written, and the fault reports
 * itself as nothing at all appearing -- no error, no entry. The rule was confirmed against ImageJ
 * 1.54p by compiling two plugins that differ in nothing else into one plugins directory and reading
 * back {@code ij.Menus.getCommands()}: {@code With_Underscore} appeared as the command "With
 * Underscore" and {@code NoUnderscore} was absent from the table entirely. The names in this
 * package carry underscores for that reason, which also keeps them consistent with the
 * {@code Step_} and {@code Activity_} naming used elsewhere in the project.
 *
 * <p>The {@code main} method runs the plugin directly, so that the example can be exercised from an
 * IDE without an ImageJ installation to deploy into.
 */
public class Ramp_Image_PlugIn implements PlugIn {

    private static final int WIDTH = 512;
    private static final int HEIGHT = 200;

    @Override
    public void run(String arg) {
        ImageProcessor ip = new ByteProcessor(WIDTH, HEIGHT);

        for (int x = 0; x < WIDTH; x++) {
            int intensity = x * 255 / (WIDTH - 1);
            for (int y = 0; y < HEIGHT; y++) {
                ip.set(x, y, intensity);
            }
        }

        ImagePlus image = new ImagePlus("Horizontal ramp, 8-bit", ip);
        image.show();
        IJ.log("Ramp_Image_PlugIn created " + WIDTH + "x" + HEIGHT + ", 8-bit grayscale");
    }

    public static void main(String[] args) {
        new Ramp_Image_PlugIn().run("");
    }
}
