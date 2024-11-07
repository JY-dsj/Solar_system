package com.neutech.solar.entity;

import com.neutech.solar.client.Constant;
import com.neutech.solar.util.ImageUtil;

import java.awt.*;

/**
 * @author 2022
 */
public class Sun extends AbstractSolarObject {

    public Sun(){
        this.img = ImageUtil.getImage("Sun.png") ;
        this.height = img.getHeight(null) ;
        this.width = img.getWidth(null);
        this.x = Constant.WINDOW_WIDTH / 2 - this.width / 2;
        this.y = Constant.WINDOW_HEIGHT / 2 - this.height / 2;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(img,x,y,null) ;
    }


}
