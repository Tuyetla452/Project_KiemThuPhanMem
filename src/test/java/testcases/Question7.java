package testcases;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.Random;

public class Question7 extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int outerRadiusStep = 30;
        int innerRadiusStep = 10;
        Random rand = new Random();

        for (int outerRadius = 30; outerRadius <= 120; outerRadius += outerRadiusStep) {

            int outerX = centerX - outerRadius;
            int outerY = centerY - outerRadius;
            int outerDiameter = 2 * outerRadius;

            for (int innerRadius = outerRadius; innerRadius >= 10; innerRadius -= innerRadiusStep) {

                int red = rand.nextInt(256);
                int green = rand.nextInt(256);
                int blue = rand.nextInt(256);
                Color randomColor = new Color(red, green, blue);

                int innerX = centerX - innerRadius;
                int innerY = centerY - innerRadius;
                int innerDiameter = 2 * innerRadius;

                g.setColor(randomColor);
                g.fillOval(innerX, innerY, innerDiameter, innerDiameter);
            }

            g.setColor(Color.BLACK);
            g.drawOval(outerX, outerY, outerDiameter, outerDiameter);
        }
    }

    public static void main(String[] args) {
        javax.swing.JFrame frame = new javax.swing.JFrame("Random Colored Concentric Circles");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(new Question7());
        frame.setVisible(true);
    }
}
