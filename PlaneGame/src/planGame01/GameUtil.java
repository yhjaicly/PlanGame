package planGame01;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class GameUtil {
    // 工具类最好将构造器私有化。
    private GameUtil() {

    }

    public static Image getImage(String path) {
        BufferedImage bi = null;
        try {
            URL u = GameUtil.class.getClassLoader().getResource(path);//获取图片的url
            bi = ImageIO.read(u);//用io流读取图片
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }

}