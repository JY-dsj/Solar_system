package com.neutech.solar.entity;

import com.neutech.solar.util.ImageUtil;

import java.awt.*;

/**
 * @author 2022
 */
public class Planet extends AbstractSolarObject {
    /**
     * 椭圆轨道的半长轴
     */
    private int longAxis;
    /**
     * 椭圆轨道的半短轴
     */
    private int shortAxis;
    /**
     * 参数()
     */
    private double theta;
    /**
     * 行星围绕太阳运行的速度
     */
    private double speed;
    /**
     * 旋转中心
     */
    private AbstractSolarObject center;

    private String name;
    /**
     * 判断是否是小行星
     */
    private boolean smallPlanet ;


    /**
     * 构造普通行星和卫星的方法
     */
    public Planet(AbstractSolarObject center ,String imgName,int no,String name,double au,int t,double e){
        this.center = center ;
        this.img = ImageUtil .getImage(imgName ) ;
        this.height = img.getHeight(null) ;
        this.width = img.getWidth(null) ;
        this.longAxis = getLongAxisByAu(au);
        this.shortAxis = getShortAxisByE(e);
        this.speed = getSpeedByT(t) ;
        this.x = center.x+ this.longAxis-this.width/2+this.center .width /2;
        this.y = center.y -this.height/2+this.center .height /2;
        this.name = name;

    }

    /**
     *构造小行星的方法
     */
    public Planet(String imgName,AbstractSolarObject center ,int no,String name,double au,int t,double e,boolean smallPlanet){
        this.center = center ;
        //从内存里的Map结构获取图片
        this.img = ImageUtil.imageMap.get(imgName) ;
        this.width = img.getWidth(null) ;
        this.height = img.getHeight(null) ;
        this.longAxis = getLongAxisByAu(au);
        this.shortAxis = getShortAxisByE(e);
        this.speed = getSpeedByT(t) ;
        this.x = center.x+ this.longAxis-this.width/2+this.center .width /2;
        this.y = center.y -this.height/2+this.center .height /2;
        this.name = name;
        this.theta = 0.0;
        this.smallPlanet = smallPlanet ;


    }
    //速度
    private double getSpeedByT(int t){
        return 365*0.015/t;
    }
    //短轴
    private int getShortAxisByE (double e){
        return (int)(this.longAxis *Math.sqrt(1-e*e) );
    }
    //长轴
    private int getLongAxisByAu (double au){
        return (int)(50*au);
    }


    @Override
    public void move() {
        x = (int) (longAxis * Math.cos(theta )+center .x +center .width /2-this .width /2
                +Math.sqrt(longAxis *longAxis -shortAxis *shortAxis ));
        y = (int) (shortAxis * Math .sin(theta)+center .y +center .height /2-this .height /2);
        theta += speed;
    }

    @Override
    public void draw(Graphics g) {
        if (!smallPlanet ){
            drawTrace(g) ;
        }
         g.drawImage(img,x,y,null);
         move();
    }

    /**
     * 自定义行星轨道方法
     * @param g 画笔对象
     */
    private void drawTrace(Graphics g){
        int x = (int)(center.x -this.longAxis + center .width /2
                +Math.sqrt(longAxis *longAxis -shortAxis *shortAxis )) ;
        int y = center .y -this.shortAxis + center.height /2;
        int width = 2*longAxis ;
        int height = 2*shortAxis ;
        Color c = g.getColor() ;
        //设置画笔
        g.setColor(Color.white);
        g.drawOval(x,y,width ,height );
        g.setColor(c) ;
        //写名字
        g.drawString(name,this. x,this.y ) ;
        g.setColor(Color.white);
        g.setColor(c) ;
    }
}
