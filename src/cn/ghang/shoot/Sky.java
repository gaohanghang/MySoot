package cn.ghang.shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 天空: 是飞行物
 */
public class Sky extends FlyingObject {
    private static BufferedImage image; //图片

    static {
        image = loadImage("background.png"); //读取图片
    }

    private int step; //移动速度
    private int y1; //用于天空图片切换

    /**
     * 构造方法
     */
    public Sky() {
        width = World.WIDTH;   //width:窗口的宽
        height = World.HEIGHT; //height:窗口的高
        x = 0;
        y = 0;
        step = 1;
        y1 = -this.height; //y1:负的天空的高
    }

    /**
     * 天空移动
     */
    public void step() {
        y += step;  //y+(向下)
        y1 += step; //y1+(向下)
        if (y >= this.height) { //当y>=窗口的高，意味着出了窗口了
            y = -this.height; //将y图片放到最上面
        }
        if (y1 >= this.height) { //当y1>=窗口的高，意味着出了窗口了
            y1 = -this.height; //将y1图片放在最上面
        }
    }

    /**
     * 重写getImage()获取图片
     */
    public BufferedImage getImage() {
        return image; //直接返回image图片
    }

    /**
     * 重写画对象paint()
     */
    public void paint(Graphics g) {
        g.drawImage(getImage(), x, y, null);  //画第1个天空
        g.drawImage(getImage(), x, y1, null); //画第2个天空
    }

    /**
     * 重写outOfBounds()判断是否越界
     */
    public boolean outOfBounds() {
        return false; //永不越界
    }

}













