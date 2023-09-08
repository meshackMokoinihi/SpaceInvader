package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyFrame extends JFrame {
    Image image;
    Graphics graphics;
    Box player;
    Box enemy;
    boolean gameover;
    Timer timer;

    MyFrame() {
        player = new Box(100, 500, 50, 50, Color.BLACK);
        enemy = new Box(200, 200, 50, 50, Color.RED);
        gameover = false;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);
        this.addKeyListener(new AL());

        // Start a timer to periodically update the frame
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.move();
                checkCollision();
                repaint();
            }
        });
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        image = createImage(this.getWidth(), this.getHeight());
        graphics = image.getGraphics();
        super.paint(graphics);
        player.draw(graphics);
        enemy.draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void checkCollision() {
        if (player.isShooting() && enemy.intersects(player.getBullet())) {
            gameover = true;
            System.out.println("GAME OVER");
        }
    }

    public class AL extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);

            // Handle shooting when spacebar is pressed
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                player.shoot();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // Reset the shooting state when spacebar is released
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                player.stopShooting();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MyFrame();
            }
        });
    }
}