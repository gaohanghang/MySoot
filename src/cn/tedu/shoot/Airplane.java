package cn.tedu.shoot;

import java.awt.image.BufferedImage;

/**
 * 小敌机: 既是飞行物，也是敌人能得分
 */
public class Airplane extends FlyingObject implements Enemy {
    private static BufferedImage[] images; //图片数组

    static {
        images = new BufferedImage[5]; //5张图片
        for (int i = 0; i < images.length; i++) { //遍历图片数组
            images[i] = loadImage("airplane" + i + ".png");
        }
    }

    private int step; //移动速度

    /**
     * 构造方法
     */
    public Airplane() {
        super(49, 36);
        this.step = 2;
    }

    /**
     * 小敌机移动
     */
    public void step() {
        y += step; //y+(向下)
    }

    int deadIndex = 1; //死了的起始下标

    /**
     * 重写getImage()获取图片
     */
    public BufferedImage getImage() {
        if (isLife()) { //若活着呢，直接返回images[0]
            return images[0];
        } else if (isDead()) { //若死了的，
            BufferedImage img = images[deadIndex++]; //返回images[1]到images[4]之间的图片对象
            if (deadIndex == images.length) { //若到最后的图片了
                state = REMOVE; //可以删除
            }
            return img;
        }
        return null;
    }

    /**
     * 重写outOfBounds()判断是否越界
     */
    public boolean outOfBounds() {
        return this.y >= World.HEIGHT; //小敌机的y>=窗口的高，即为越界了
    }

    /** 重写getScore()得分 */
    public int getScore() {
        return 1; //打掉一个小敌机，得1分
    }
}












