package planGame01;

import java.awt.*;

/*爆炸类，用于飞机碰撞后产生的特效
 * */
public class Explode {
    double x, y;//飞机爆炸时的位置
    static Image[] img = new Image[16];//16张爆炸图，用于轮播
    int count = 0;//计数爆炸图片

    public Explode(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    static {
        for (int i = 0; i < 16; i++) {
            img[i] = GameUtil.getImage("images/explode/e" + (i+1) + ".gif");//获取图片
            img[i].getWidth(null);
        }
    }
    public void draw(Graphics g) {//画ba爆炸效果
      if (count < 16) {
            g.drawImage(img[count], (int) x, (int) y, null);
            count++;
        }
    }
}
