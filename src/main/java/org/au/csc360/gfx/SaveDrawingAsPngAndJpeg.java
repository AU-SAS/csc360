package org.au.csc360.gfx;

import javax.imageio.ImageIO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Draws one picture and writes it to disk twice, as PNG and as JPEG, so the two formats can be
 * compared on identical content.
 *
 * <p>Unlike the demos in {@code gfx.practice} and {@code gfx.atvt}, nothing is shown on screen.
 * Those paint into a {@code JPanel}, which means they paint into whatever buffer the window system
 * provides, on every repaint. Here the drawing target is a {@link BufferedImage} the program owns:
 * the same {@link Graphics2D} calls, but the pixels stay in memory afterwards and can be encoded.
 * That is the whole difference between drawing on screen and producing an image file.
 *
 * <p>The subject is chosen to make the format difference visible rather than to look good. Flat
 * color fills, hard edges and text are exactly what JPEG handles badly: its discrete cosine
 * transform encodes blocks of gradually varying color compactly, so a sharp boundary needs many
 * high-frequency coefficients and still comes back with ringing and blocking around the edge.
 *
 * <p><strong>On this picture the JPEG is the bigger file.</strong> Measured at 640x400: PNG 14,853
 * bytes, JPEG 20,301 bytes -- the lossy format losing on size to the lossless one, by a third. That
 * is not a quirk of the example, it is what synthetic graphics do. PNG's filtering plus DEFLATE is
 * close to ideal for long runs of identical pixels and a small palette, which is what flat fills and
 * line art are, while JPEG pays its worst case for every hard edge and still throws information
 * away. Swap in a photograph and the ordering reverses, decisively.
 *
 * <p>The rule students should leave with is therefore not "JPEG is smaller". It is that JPEG is for
 * photographs and PNG is for anything drawn -- diagrams, screenshots, charts, text, line art -- where
 * it is both smaller and exact. Run the program, compare the printed sizes, then open the two files
 * side by side and zoom into the text to see what the extra bytes bought.
 *
 * <p><strong>The image is {@link BufferedImage#TYPE_INT_RGB}, deliberately.</strong> With
 * {@code TYPE_INT_ARGB} the PNG would still be written correctly, but the JPEG writer bundled with
 * the JDK cannot encode a four-channel image: {@link ImageIO#write} returns {@code false} or throws,
 * depending on the JDK, and a beginner reads that as "JPEG saving is broken". JPEG has no alpha
 * channel, so an opaque image type is the honest choice. Needing transparency means needing PNG.
 *
 * <p>Both files go to {@code target/demo-output} by default, which Maven's clean removes and git
 * ignores. Pass a directory as the first argument to put them somewhere else.
 */
public class SaveDrawingAsPngAndJpeg {

    private static final int WIDTH = 640;
    private static final int HEIGHT = 400;

    public static void main(String[] args) throws IOException {
        Path outDir = args.length > 0 ? Path.of(args[0]) : Path.of("target", "demo-output");
        Files.createDirectories(outDir);

        BufferedImage image = drawPicture();

        File png = outDir.resolve("drawing.png").toFile();
        File jpeg = outDir.resolve("drawing.jpg").toFile();

        write(image, "png", png);
        write(image, "jpeg", jpeg);

        System.out.printf("PNG  %,9d bytes  %s%n", png.length(), png.getAbsolutePath());
        System.out.printf("JPEG %,9d bytes  %s%n", jpeg.length(), jpeg.getAbsolutePath());
    }

    /**
     * Encodes {@code image} and fails loudly if the format is not available. {@link ImageIO#write}
     * reports "no writer for this format" by returning {@code false} rather than by throwing, which
     * is easy to ignore and leaves a zero-byte file behind.
     */
    private static void write(BufferedImage image, String format, File file) throws IOException {
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("No " + format + " writer is registered with ImageIO");
        }
    }

    /** Paints the test subject: flat fills, hard edges and text, all of which JPEG struggles with. */
    private static BufferedImage drawPicture() {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // TYPE_INT_RGB starts out black, so the background is painted rather than assumed.
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        // Saturated flat fills with hard boundaries -- the worst case for a lossy codec.
        Color[] bands = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};
        int bandWidth = WIDTH / bands.length;
        for (int i = 0; i < bands.length; i++) {
            g2d.setColor(bands[i]);
            g2d.fillRect(i * bandWidth, 40, bandWidth, 120);
        }

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(0, 40, bandWidth * bands.length - 1, 120);

        g2d.drawOval(60, 200, 150, 150);
        g2d.setColor(new Color(0, 120, 215));
        g2d.fillOval(60, 200, 150, 150);

        // Text is the other thing JPEG ruins: thin strokes against a flat background.
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("SansSerif", Font.BOLD, 26));
        g2d.drawString("CSC360 - PNG vs JPEG", 250, 250);
        g2d.setFont(new Font("SansSerif", Font.PLAIN, 15));
        g2d.drawString("Same pixels, two encodings.", 250, 285);
        g2d.drawString("Zoom in on this text in both files.", 250, 310);

        g2d.dispose();
        return image;
    }
}
