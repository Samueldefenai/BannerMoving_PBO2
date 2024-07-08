package com.my.movingbanner;

import javax.swing.*;
import java.awt.*;

public class MovingBanner extends JPanel implements Runnable {
    private String text;
    private int x = 0;
    private int y;
    private int panelWidth;
    private int textWidth;

    public MovingBanner(String text) {
        this.text = text;
        this.y = 50;
        this.panelWidth = 400;
        setPreferredSize(new Dimension(panelWidth, 100));
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("SansSerif", Font.BOLD, 24));
        FontMetrics fm = g.getFontMetrics();
        textWidth = fm.stringWidth(text);
        g.drawString(text, x, y);
    }

    @Override
    public void run() {
        while (true) {
            x += 5;
            if (x > panelWidth) {
                x = -textWidth;
            }
            repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String inputText = JOptionPane.showInputDialog("Enter text to display:");
            JFrame frame = new JFrame("Moving Banner");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new MovingBanner(inputText));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}