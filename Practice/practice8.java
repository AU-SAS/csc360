import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class practice8 implements PlugInFilter {

    @Override
    public int setup(String args, ImagePlus im) {
        return DOES_8G;
    }

    @Override
    public void run(ImageProcessor ip) {
        int M = ip.getWidth();
        int N = ip.getHeight();

        for (int u = 0; u < M; u++) {
            for (int v = 0; v < N; v++) {
                int p = ip.getPixel(u, v);
                ip.putPixel(u, v, 255 - p);
            }
        }
    }
}
