package planGame01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/*飞机大战
 *Mr.Yin HaiJun
 *
 * */
public class MyGameFrame extends JFrame {
    Image bg = GameUtil.getImage("images/bg.jpg");//加载背景图片
    Image planImg = GameUtil.getImage("images/fj.png");//加载飞机图片
    Plane plane = new Plane(planImg, 250, 250);//飞机的起始位置
    Shell[] shells = new Shell[50];//创建50个子弹
    Explode bao = null;//爆炸对象

    Date startTime = new Date();
    Date endTime = null;
    int gameTime;//游戏的时间

    //初始化窗口
    public void launchFrame() {
        this.setTitle("飞机大战");//游戏的标题
        this.setVisible(true);//显示窗口
        this.setSize(Constructor.WIDTH, Constructor.HIGHT);//设置窗口大小
        this.setLocation(300, 300);//设置窗口位置
        this.addWindowListener(new WindowAdapter() {//关闭游戏
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });//匿名内部类，用来关闭游戏
        new PainThread().start();//启动重画线程
        addKeyListener(new KeyMonitor());//增加键盘的监听

        for (int i = 0; i < shells.length; i++) {//new50个炮弹
            shells[i] = new Shell();//实例化炮弹
        }
    }

    @Override
    public void paint(Graphics g) {//自动被调用，g相当于一直画笔
        super.paint(g);
        g.drawImage(bg, 0, 0, null);//画背景
        plane.drawSelf(g);//画飞机

        //画50个炮弹
        for (int i = 0; i < shells.length; i++) {
            if (shells[i]!=null){
                shells[i].drawShell(g);//画一个炮弹
                boolean peng = shells[i].getRect().intersects(plane.getRect());//用于碰撞检测
                if (peng) {
                    plane.live = false;//飞机消失
                    if (bao == null) {
                        bao = new Explode(plane.x, plane.y);//将飞机消失的位置作为爆炸位置
                        endTime = new Date();
                        gameTime = (int)((endTime.getTime()-startTime.getTime())/1000);
                    }
                    bao.draw(g);//画爆炸效果
                }
                if (!plane.live){
                    g.setColor(Color.WHITE);
                    Font f = new Font("宋体", Font.BOLD, 50);
                    g.setFont(f);
                    g.drawString("时间："+gameTime+"秒", 120, 260);
                }
            }
        }
    }
    //这个类帮助我们反复的画窗口
    class PainThread extends Thread {
        @Override
        public void run() {//线程启动
            super.run();
            while (true) {
                repaint();//重画
                try {
                    Thread.sleep(40);//1s=1000ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*定义键盘接听内部类
     * */
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {//按下
            super.keyPressed(e);
            plane.addDirection(e);//向按的方向移动
        }

        @Override
        public void keyReleased(KeyEvent e) {//抬起
            super.keyReleased(e);
            plane.minusDirection(e);//取消该方向移动
        }
    }


    public static void main(String[] args) {
        MyGameFrame myGameFrame = new MyGameFrame();//游戏入口
        myGameFrame.launchFrame();//游戏的窗口
    }
    //添加双缓冲技术
    private Image offScreenImage = null;
    public void update(Graphics g) {
        if(offScreenImage == null)
            offScreenImage = this.createImage(Constructor.WIDTH,Constructor.HIGHT);//这是游戏窗口的宽度和高度
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

}
