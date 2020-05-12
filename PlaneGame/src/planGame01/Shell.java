package planGame01;

import java.awt.*;

/*炮弹类
* */
public class Shell extends GameObject {
    public double degree;

    public Shell(){
        degree = Math.random()*Math.PI*2;//随机的角度
        x = 200;
        y = 200;
        width = 10;
        height = 10;
        speed = 3;
    }

    public void drawShell(Graphics g){
        Color c= g.getColor();//保留初始画笔状态

        g.setColor(Color.YELLOW);
        g.fillOval((int)x, (int)y, width, height);

        //炮弹沿任意方向飞行

        x += speed*Math.cos(degree);
        y += speed*Math.sin(degree);

        //判断是否碰到边界
        if (x<0|| x>Constructor.WIDTH-width){
            degree = Math.PI - degree;
        }
        if (y<30 || y>Constructor.HIGHT-height){
            degree = -degree;
        }
        g.setColor(c);//恢复画笔颜色
    }

}
