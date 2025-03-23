import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class ShapeInteraction extends JPanel
{
    // Circle properties
    private int circleX = 50;
    private int circleY = 50;
    private final int circleDiameter = 40;
    private Color circleColor = Color.RED;

    // Rectangle properties
    private final int rectX = 200;
    private final int rectY = 100;
    private final int rectWidth = 100;
    private final int rectHeight = 60;

    public ShapeInteraction()
    {
        // Set panel focus for key events
        setFocusable(true);

        // Add key listener to move the circle
        addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                moveCircle(e);
                repaint(); // Redraw after moving
            }
        });
    }

    // Move the circle with arrow keys
    private void moveCircle(KeyEvent e)
    {
        int step = 10;

        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                circleY -= step;
                break;
            case KeyEvent.VK_DOWN:
                circleY += step;
                break;
            case KeyEvent.VK_LEFT:
                circleX -= step;
                break;
            case KeyEvent.VK_RIGHT:
                circleX += step;
                break;
        }

        // Check for collision with the rectangle
        checkCollision();
    }

    // Check if the circle collides with the rectangle
    private void checkCollision()
    {
        Rectangle circleBounds = new Rectangle(circleX, circleY, circleDiameter, circleDiameter);
        Rectangle rectBounds = new Rectangle(rectX, rectY, rectWidth, rectHeight);

        if (circleBounds.intersects(rectBounds))
        {
            // Change color on collision
            circleColor = Color.GREEN;
        }
        else
        {
            // Revert to original color if not colliding
            circleColor = Color.RED;
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw the rectangle
        g2d.setColor(Color.BLUE);
        g2d.fillRect(rectX, rectY, rectWidth, rectHeight);

        // Draw the circle
        g2d.setColor(circleColor);
        g2d.fillOval(circleX, circleY, circleDiameter, circleDiameter);

        // Draw outline for better visibility
        g2d.setColor(Color.BLACK);
        g2d.drawOval(circleX, circleY, circleDiameter, circleDiameter);
    }

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Shape Interaction Demo");
        ShapeInteraction panel = new ShapeInteraction();

        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
