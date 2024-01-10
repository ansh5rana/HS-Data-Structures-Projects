import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class SierpinskiHexagon extends JPanel implements KeyListener {
    private ArrayList<Point> points;
    private double percentage = 0.5;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sierpinski Hexagon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        SierpinskiHexagon hexagonPanel = new SierpinskiHexagon();
        frame.setContentPane(hexagonPanel);
        frame.setVisible(true);
        frame.repaint();

        JScrollBar scrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 50, 1, 1, 100);
        scrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                hexagonPanel.setPercentage(e.getValue() / 100.0);
            }
        });

        scrollBar.setPreferredSize(new Dimension(800, 30));
        frame.add(scrollBar, BorderLayout.SOUTH);
    }

    public SierpinskiHexagon() {
        points = new ArrayList<>();
        createCorners();
        createRandomPoint();
        startSierpinski();
        setFocusable(true);
        addKeyListener(this);
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public void createCorners() {
        int centerX = 400;
        int centerY = 400;
        int radius = 300;
        for (int i = 0; i < 6; i++) {
            int x = centerX + (int) (radius * Math.cos(Math.toRadians(60 * i)));
            int y = centerY + (int) (radius * Math.sin(Math.toRadians(60 * i)));
            points.add(new Point(x, y, Color.WHITE));
        }
    }

    public void createRandomPoint() {
        int[] xValues = new int[6];
        int[] yValues = new int[6];
        for (int i = 0; i < 6; i++) {
            xValues[i] = points.get(i).x;
            yValues[i] = points.get(i).y;
        }
        Polygon hexagon = new Polygon(xValues, yValues, 6);
        Random rand = new Random();
        int x, y;

        do {
            x = rand.nextInt(800);
            y = rand.nextInt(800);
        } while (!hexagon.contains(x, y));

        points.add(new Point(x, y, Color.WHITE));
    }

    public void startSierpinski() {
        createNewPoint();
        repaint();
    }

    public void createNewPoint() {
        Random rand = new Random();
        int cornerIndex = rand.nextInt(6);
        Point corner = points.get(cornerIndex);
        Point lastPoint = points.get(points.size() - 1);
        int newX = (int) (corner.x + (lastPoint.x - corner.x) * percentage);
        int newY = (int) (corner.y + (lastPoint.y - corner.y) * percentage);
        points.add(new Point(newX, newY, Color.WHITE));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);
        for (Point point : points) {
            g.setColor(point.color);
            g.fillOval(point.x, point.y, 2, 2);
        }
    }

    /* 
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == '1') {
            percentage = 0.5;
        } else if (e.getKeyChar() == '2') {
            percentage = 0.55;
        } else if (e.getKeyChar() == '3') {
            percentage = 0.575;
        } else if (e.getKeyChar() == '4') {
            percentage = 2.0 / 3.0;
        }
        startSierpinski();
    }*/

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == '5') {
            startSierpinski();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    class Point {
        int x, y;
        Color color;

        public Point(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
}

