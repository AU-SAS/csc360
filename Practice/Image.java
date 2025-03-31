import ij.ImageJ;
import ij.ImagePlus;
import ij.io.Opener;

public class Image {
    public static void main(String[] args) {
        new ImageJ();
        Opener opener = new Opener();
        ImagePlus img = opener.openImage("path/to/image.jpg");
        if (img != null) img.show();
    }
}
