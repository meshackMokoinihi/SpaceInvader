package org.example;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Box extends Rectangle {
    private int bulletY;
    private int bulletX;
    private Rectangle bullet;
    private boolean shooting = false;
    private Color color;

    Box(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.x = x - 25;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.x = x + 25;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.y = y - 25;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.y = y + 25;
        }
    }

    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.x, this.y, this.width, this.height);

        if (shooting) {
            g.setColor(Color.RED);
            g.fillOval(bullet.x, bullet.y, 10, 10);
        }
    }

    public void shoot() {
        if (!shooting) {
            bullet = new Rectangle(this.x + this.width / 2 - 5, this.y, 10, 15);
            bulletY = bullet.y;
            bulletX = bullet.x;
            shooting = true;
        }
    }

    public void stopShooting() {
        shooting = false;
    }

    public boolean isShooting() {
        return shooting;
    }

    public Rectangle getBullet() {
        return bullet;
    }

    public void move() {
        if (shooting) {
            bulletY -= 10;
            bullet.setLocation(bulletX, bulletY);
        }
    }
}