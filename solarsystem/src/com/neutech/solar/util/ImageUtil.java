package com.neutech.solar.util;

import com.neutech.solar.client.Constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理项目中的静态资源
 * @author 2022
 */
public final class ImageUtil {
    public static Map<String ,Image> imageMap = new HashMap<>();
    static {
        imageMap.put("smallPlanet",ImageUtil .getImage("pluto.png") );
    }
    private ImageUtil(){}

    /**
     * 从硬盘中获取图片
     * @param imgName 图片名
     * @return Image 对象
     */
    public static Image getImage (String imgName){
        BufferedImage img = null;
        URL url = ImageUtil .class.getClassLoader().getResource(Constant .IMG_PRE + imgName);
        try {
            img = ImageIO.read(url);
        } catch (Exception e){
            e.printStackTrace();
        }
        return img;
    }
}
