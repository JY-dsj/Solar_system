package com.neutech.solar.entity;

import com.neutech.solar.client.Constant;
import com.neutech.solar.util.ImageUtil;

import java.awt.*;

/**
 * @author 2022
 */
public class Background extends AbstractSolarObject {
    public Background(String imgName) {
        this.x = 0;
        this.y = 0;
        this.img = ImageUtil.getImage(imgName) ;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img,x,y,null);
    }
}
