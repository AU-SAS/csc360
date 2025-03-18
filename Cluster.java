import javax.swing.*;
import java.awt.*;
public class Cluster extends JPanel
{
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5)); }
}
