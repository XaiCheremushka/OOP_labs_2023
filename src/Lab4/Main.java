package Lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JPanel implements ActionListener {
    private int planetRadius = 100;  
    private int satelliteRadius = 10; 
    private int satelliteX, satelliteY;
    private double angle = 0; 
    private Timer timer;

    private boolean isSatelliteVisible = true;

    public Main() {
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {        
        angle += 0.02;

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int a = 150; 
        int b = 80; 
        satelliteX = centerX + (int) (a * Math.cos(angle));
        satelliteY = centerY + (int) (b * Math.sin(angle));

        isSatelliteVisible = isSatelliteVisible(centerX, centerY);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        g.setColor(Color.BLUE);
        g.fillOval(centerX - planetRadius, centerY - planetRadius, 2 * planetRadius, 2 * planetRadius);

        if (isSatelliteVisible) {
            g.setColor(Color.GRAY);
            g.fillOval(satelliteX - satelliteRadius, satelliteY - satelliteRadius, 2 * satelliteRadius, 2 * satelliteRadius);
        }
    }

    private boolean isSatelliteVisible(int centerX, int centerY) {
        if (satelliteY < centerY) {
            double distance = Math.sqrt(Math.pow(centerX - satelliteX, 2) + Math.pow(centerY - satelliteY, 2));
            return distance > planetRadius + satelliteRadius;
        }
        else
            return true;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Лабораторная работа 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new Main());
        frame.setVisible(true);
    }
}
