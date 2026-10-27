package org.au.csc360.ij;

import ij.IJ;
import ij.ImagePlus;

import java.io.File;
import java.nio.file.Path;

/**
 * Opens a PNG and a JPEG with ImageJ and reports what ImageJ makes of each.
 *
 * <p>This is the hand-off from drawing to image processing. Everything in {@code org.au.csc360.gfx}
 * produces pixels; ImageJ is the toolkit the rest of the course uses to measure and transform them.
 * The bridge is one call, {@link IJ#openImage(String)}, which decodes a file into an
 * {@link ImagePlus} -- ImageJ's image object, carrying the pixels plus the calibration, slice and
 * type information its plugins expect.
 *
 * <p>Note what the two files have in common once opened. The PNG and the JPEG report the same
 * dimensions and the same 24-bit RGB type, because the format is a property of the file on disk,
 * not of the image in memory: decoding throws the container away. What the JPEG does not get back
 * is the exact pixel values it was given, and no inspection after loading can recover them. Which
 * is the practical reason image processing starts from lossless sources -- a measurement made on a
 * JPEG is a measurement partly of the codec.
 *
 * <p><strong>On the package name.</strong> This package is {@code org.au.csc360.ij} while ImageJ's
 * own root package is {@code ij}, which looks like a collision and is not one. Java resolves a
 * package name absolutely, always from the root: there is no relative lookup that would let
 * {@code org.au.csc360.ij} be reached as plain {@code ij} from inside {@code org.au.csc360}, and a
 * package is never "in scope" the way a class member is. So {@code ij.IJ} means the ImageJ class
 * everywhere, including in this file and in its parent package. Checked rather than assumed, by
 * compiling three cases: a qualified {@code ij.IJ} from {@code org.au.csc360}, the same from inside
 * {@code org.au.csc360.ij}, and a wildcard {@code import ij.*} from inside it. All three compile.
 *
 * <p>The intuition that they would clash is imported from languages where a nested namespace does
 * shadow an outer one -- C++, C#, Python's relative imports. In Java the only names that shadow are
 * type names, which is why a <em>class</em> here called {@code IJ} would genuinely be a problem and
 * a package called {@code ij} is not.
 *
 * <p>In the absence of arguments the class opens the two files written by
 * {@code org.au.csc360.gfx.SaveDrawingAsPngAndJpeg}, which must therefore have run beforehand. Paths
 * supplied as arguments are opened instead.
 */
public class LoadPngAndJpegWithImageJ {

    private static final Path DEFAULT_DIR = Path.of("target", "demo-output");

    public static void main(String[] args) {
        String[] paths = args.length > 0 ? args : new String[] {
                DEFAULT_DIR.resolve("drawing.png").toString(),
                DEFAULT_DIR.resolve("drawing.jpg").toString()
        };

        for (String path : paths) {
            open(path);
        }
    }

    private static void open(String path) {
        File file = new File(path);
        if (!file.isFile()) {
            System.out.println("Not found: " + file.getAbsolutePath());
            System.out.println("  Run org.au.csc360.gfx.SaveDrawingAsPngAndJpeg first to create it.");
            return;
        }

        // Returns null on an unreadable or unrecognised file rather than throwing.
        ImagePlus image = IJ.openImage(file.getAbsolutePath());
        if (image == null) {
            System.out.println("ImageJ could not decode: " + file.getAbsolutePath());
            return;
        }

        System.out.printf("%-14s %4d x %-4d  %2d-bit  %-10s %,9d bytes on disk%n",
                file.getName(),
                image.getWidth(),
                image.getHeight(),
                image.getBitDepth(),
                typeName(image.getType()),
                file.length());

        image.show();
    }

    /** ImageJ reports its type as an int constant; this turns it into something readable. */
    private static String typeName(int type) {
        switch (type) {
            case ImagePlus.GRAY8:    return "8-bit gray";
            case ImagePlus.GRAY16:   return "16-bit gray";
            case ImagePlus.GRAY32:   return "32-bit float";
            case ImagePlus.COLOR_256: return "8-bit color";
            case ImagePlus.COLOR_RGB: return "RGB";
            default:                 return "type " + type;
        }
    }
}
