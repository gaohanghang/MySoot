package cn.tedu.shoot;

import java.awt.image.BufferedImage;

/**
 * 子弹: 是飞行物
 */
public class Bullet extends FlyingObject {
    private static BufferedImage image; //图片

    static {
        image = loadImage("bullet.png"); //读取图片
    }

    private int step; //移动速度

    /**
     * 构造方法
     */
    public Bullet(int x, int y) {
        width = 8;
        height = 14;
        this.x = x;
        this.y = y;
        step = 3;
    }

    /**
     * 子弹移动
     */
    public void step() {
        y -= step; //y-(向上)
    }

    /**
     * 重写getImage()获取图片
     */
    public BufferedImage getImage() {
        if (isLife()) {     //若活着呢，
            return image;
        } else if (isDead()) { //若死了，则修改状态为删除
            state = REMOVE; //可以删除了
        }
        return null;
    }

    /**
     * 重写outOfBounds()判断是否越界
     */
    public boolean outOfBounds() {
        return this.y <= -this.height; //子弹的y<=负的子弹的高，即为越界了
    }

}













