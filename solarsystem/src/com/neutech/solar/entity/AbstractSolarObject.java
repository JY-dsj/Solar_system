package com.neutech.solar.entity;

import java.awt.*;

/**
 * @author 2022
 */
public abstract class AbstractSolarObject implements IDrawable,IMoveable {
    public int x;
    /**
     * x是横坐标
     */
    public int y;
    /**
     *  y是纵坐标
     */
    public Image img;
    /**
     * img是图片对象
     */


    public int width;
    /**
     * 图片的宽
     */
    public int height;
    /**
     * 图片的高
     */

    @Override
    public void draw(Graphics g) {}

    @Override
    public void move() {}
}
