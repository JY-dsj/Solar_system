package com.neutech.solar.client;

import com.neutech.solar.entity.Background;
import com.neutech.solar.entity.Planet;
import com.neutech.solar.entity.Sun;
import com.neutech.solar.util.ImageUtil;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 2022
 */
public class SolarSystemClient extends Frame  {
    public Background bg = new Background("bg.jpg");
    public Sun sun = new Sun();
    public Planet  mercury= new Planet(sun,"mercury.png",1,"水星",0.387,88,0.2056) ;
    public Planet venus = new Planet(sun,"Venus.png",2,"金星",0.72,225,0.0068) ;
    public Planet earth = new Planet(sun,"Earth.png",3,"地球",1,365,0.0167) ;
    public Planet astronauts = new Planet(sun,"Astronauts.png",13,"宇航员",1,365,0.0164) ;
    public Planet moon = new Planet(earth ,"moon.png",10,"月球",0.2,365/12,0.0) ;
    public Planet  satellite= new Planet(earth,"Satellite.png",11,"卫星",0.15,365/20,0.0) ;
    public Planet  mars= new Planet(sun,"Mars.png",4,"火星",1.52,687,0.0934) ;
    public Planet  satellite1= new Planet(mars,"Satellite.png",12,"卫星",0.2,365/20,0.0) ;


    public List<Planet> smallPlanets = new ArrayList<>() ;
    {
        for(int i=0;i<500000;i++){
            double au = Math.random() *2+2;
            int  t = (int) (Math.random() * 3300) +700;
            Planet smallPlanet = new Planet("smallPlanet",sun,14,"",au,t,0,true) ;
            smallPlanets .add(smallPlanet ) ;
        }
    }

    public Planet  jupiter= new Planet(sun,"Jupiter.png",5,"木星",5.2,4330,0.0489) ;
    public Planet  saturn= new Planet(sun,"Saturn.png",6,"土星",9.54,10832,0.0557) ;
    public Planet  uranus= new Planet(sun,"Uranus.png",7,"天王星",19.218,30777,0.0444) ;
    public Planet  neptune= new Planet(sun,"Neptune.png",8,"海王星",30.06,60328,0.0112) ;
    public Planet  pluto= new Planet(sun,"pluto.png",9,"冥王星",39.68,90717,0.249) ;
    public Planet  hally = new Planet(sun,"Mars.png",13,"哈雷彗星",17.8,27485,0.967) ;

    /**
     * 自定义加载窗口的方法
     */
    public void loadFrame(){
        //设置窗口大小
        setSize(Constant .WINDOW_WIDTH ,Constant .WINDOW_HEIGHT ) ;
        //窗口位置
        setLocationRelativeTo(null) ;
        //窗口的可见性
        setVisible(true) ;
        //窗口LOGO
        setIconImage(ImageUtil.getImage("Sun.png"));
        //窗口标题
        setTitle("太阳系八大行星运行系统") ;
        //重写父类关闭窗口的方法
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System .exit(0) ;
            }
        });
        //启动线程的方法
        new AnimationThread() .start() ;
    }

    /**
     * 所有绘制该窗口的内容全都要放入paint方法中
     * @param g 画笔对象
     */
    @Override
    public void paint(Graphics g) {
        bg.draw(g) ;
        //sun
        sun.draw(g) ;
        //mercury
        mercury .draw(g) ;
        //venus
        venus .draw(g ) ;
        //earth
        earth.draw(g) ;

        astronauts .draw(g) ;
        //Mars
        mars .draw(g) ;
        //jupiter
        jupiter.draw(g );
        saturn .draw(g) ;
        uranus .draw(g) ;
        neptune .draw(g) ;
        pluto .draw(g) ;
        satellite.draw(g ) ;
        moon.draw(g ) ;

        //
        smallPlanets .forEach(smallPlanets ->{
            smallPlanets .draw(g ) ;
        }) ;
        hally .draw(g);
        satellite1 .draw(g) ;
    }
    /**
     * 自定义一个动态线程
     */
    class AnimationThread extends Thread {
        @Override
        public void run() {
             while(true){
                 repaint();
                 //设置线程的睡眠时间
                 try{
                     Thread.sleep(40) ;
                 }catch (Exception e){
                     e.printStackTrace() ;
                 }
             }
        }
    }
    // 解决图片闪烁的问题，用双缓冲方法解决闪烁问题
    Image backImg = null;

    // 重写update()方法，在窗口的里层添加一个虚拟的图片
    @Override
    public void update(Graphics g) {
        if (backImg == null) {
// 如果虚拟图片不存在，创建一个和窗口一样大小的图片
            backImg = createImage(Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
        }
// 获取到虚拟图片的画笔
        Graphics backg = backImg.getGraphics();
        Color c = backg.getColor();
        backg.setColor(Color.GRAY);
        backg.fillRect(0, 0, Constant.WINDOW_WIDTH, Constant.WINDOW_HEIGHT);
        backg.setColor(c);
// 调用虚拟图片的paint()方法，每50ms刷新一次
        paint(backg);
        g.drawImage(backImg, 0, 0, null);
    }

    public static void main(String[] args) {
        new SolarSystemClient() .loadFrame() ;
    }
}
