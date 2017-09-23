package cn.ghang.shoot;

import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;

/**
 * 飞行物
 */
public abstract class FlyingObject {
    public static final int LIFE = 0; //活着的
    public static final int DEAD = 1; //死了的(但没有删除呢)
    public static final int REMOVE = 2; //可以删除的
    protected int state = LIFE; //当前状态(默认活着的)

    protected int width; //宽
    protected int height; //高
    protected int x; //x坐标
    protected int y; //y坐标

    /**
     * 读取图片资源
     */
    public static BufferedImage loadImage(String fileName) {
        try {
            BufferedImage image = ImageIO.read(FlyingObject.class.getResource(fileName)); //读取同包中的图片资源
            return image;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 默认调用的构造方法(英雄机、子弹、天空)
     */
    public FlyingObject() {
    }

    /**
     * 专门给小敌机、大敌机、小蜜蜂提供的构造
     */
    public FlyingObject(int width, int height) {
        this.width = width;
        this.height = height;
        Random rand = new Random(); //随机数对象
        x = rand.nextInt(World.WIDTH - this.width); //x:0到(400-小敌机的宽)之间的随机数
        y = -this.height; //y:负的小敌机的高
    }

    /**
     * 飞行物移动
     */
    public abstract void step();

    /**
     * 获取对象的图片
     */
    public abstract BufferedImage getImage();

    /**
     * 判断飞行物是否越界
     */
    public abstract boolean outOfBounds();

    /**
     * 画对象
     */
    public void paint(Graphics g) {
        g.drawImage(getImage(), x, y, null); //画具体的对象
    }

    /**
     * 判断对象是否活着呢
     */
    public boolean isLife() {
        return state == LIFE;
    }

    /**
     * 判断对象是否死了(但没删除)
     */
    public boolean isDead() {
        return state == DEAD;
    }

    /**
     * 判断对象是否可以被删除了
     */
    public boolean isRemove() {
        return state == REMOVE;
    }

    /**
     * 修改对象的生命周期为已经死了
     */
    public void goDead() {
        state = DEAD; //修改为已经死了
    }

    /**
     * 敌人与飞行物(子弹或英雄机)碰撞
     */
    public boolean hit(FlyingObject other) {
        //this:敌人    other:子弹或英雄机
        int x1 = this.x - other.width;  //x1:敌人的x-子弹/英雄机的宽
        int x2 = this.x + this.width;   //x2:敌人的x+敌人的宽
        int y1 = this.y - other.height; //y1:敌人的y-子弹/英雄机的高
        int y2 = this.y + this.height;  //y2:敌人的y+敌人的高
        int x = other.x;              //x:子弹/英雄机的x
        int y = other.y;              //y:子弹/英雄机的y

        return x >= x1 && x <= x2
                &&
                y >= y1 && y <= y2; //x在x1与x2之间，并且，y在y1与y2之间，即为撞上了
    }
}














