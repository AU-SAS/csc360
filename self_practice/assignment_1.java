import javax.swing.*;
import java.awt.*;

public class assignment_1 extends JFrame
{
    private String mainshapes = "empty";
    private Color maincolor = Color.WHITE;

    public assignment_1()
    {
        setTitle("Assignment 1 - shape and color");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,700);
        setLayout(new BorderLayout());


        //left
        JPanel shapepanel = new JPanel();
        shapepanel.setLayout(new GridLayout(5,1));
        String[] shapes = {"Circle", "Rectangle", "Triangle", "Oval", "Polygon"};
        for (int i = 0; i < shapes.length; i++)
        {
            String selectshape = shapes[i];
            JButton shape = new JButton(shapes[i]);
            shape.addActionListener(e -> {mainshapes = selectshape; repaint(); });
            shapepanel.add(shape);
        }


        //right
        JPanel colorpanel = new JPanel();
        colorpanel.setLayout(new GridLayout(7,1));
        String[] colors = {"Violet", "Indigo", "Blue", "Green", "Yellow", "Orange", "Red"};
        Color[] colornames =
                {
                new Color(138, 43, 226),
                new Color(75, 0, 130),
                Color.BLUE,
                Color.GREEN,
                Color.yellow,
                Color.orange,
                Color.RED};

        for (int i = 0; i < colors.length; i++)
        {

            JButton color = new JButton(colors[i]);
            Color selectcolor = colornames[i];
            color.setBackground(selectcolor);
            color.addActionListener(e -> {maincolor = selectcolor; repaint(); });
            colorpanel.add(color);
        }

        //main panel
        JPanel mainpanel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(maincolor);

                int width = getWidth()/2;
                int height = getHeight()/2;

                switch(mainshapes){

                    case "Circle":
                        g2d.fillOval(width-50 ,height-50 ,100,100);
                        break;

                    case "Rectangle":
                        g2d.fillRect(width-50 ,height-50 ,100,100);
                        break;

                    case "Triangle":
                        int[] xaxis = {width, width - 50, width + 50};
                        int[] yaxis = {height - 50, height + 50, height + 50};
                        g2d.fillPolygon(xaxis, yaxis, 3);
                        break;

                    case "Oval":
                        g2d.fillOval(width-50 ,height-50 ,50,100);
                        break;

                    case "Polygon":
                        int[] xpoly = {width-23, width - 65, width + 66, width + 70};
                        int[] ypoly = {height - 70, height + 88, height + 53, height + 70};
                        g2d.fillPolygon(xpoly, ypoly, 4);
                        break;
                }

            }
        };

        add(shapepanel, BorderLayout.WEST);
        add(colorpanel, BorderLayout.EAST);
        add(mainpanel, BorderLayout.CENTER);
        setVisible(true);


    }



    public static void main(String[] args)
    {
        new assignment_1();
    }

}
