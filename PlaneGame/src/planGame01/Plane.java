package planGame01;

import java.awt.*;
import java.awt.event.KeyEvent;

/*飞机类，定义飞机的移动，以及存活
 * */
public class Plane extends GameObject {
    boolean left, up, right, down;
    boolean live = true;
    @Override
    public void drawSelf(Graphics g) {
        if (live) {
            //super.drawSelf(g);
            g.drawImage(img, (int) x, (int) y, null);

            if (left) {
                x -= Constructor.speed;
            }
            if (right) {
                x += Constructor.speed;
            }
            if (up) {
                y -= Constructor.speed;
            }
            if (down) {
                y += Constructor.speed;
            }
        }
    }

    public Plane(Image img, double x, double y) {
        //super(img, x, y);
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = img.getWidth(null);
        this.height = img.getHeight(null);
    }

    //按下某个键，增加相应的方向
    public void addDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
    }

    //按下某个键，取消相应的方向
    public void minusDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }
    }

}
