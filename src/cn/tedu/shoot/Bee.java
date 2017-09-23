package cn.tedu.shoot;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 小蜜蜂: 既是飞行物，也是奖励
 */
public class Bee extends FlyingObject implements Award {
    private static BufferedImage[] images; //图片数组

    static {
        images = new BufferedImage[5]; //5张图片
        for (int i = 0; i < images.length; i++) { //遍历图片数组
            images[i] = loadImage("bee" + i + ".png"); //读取图片
        }
    }

    private int xStep; //x坐标移动速度
    private int yStep; //y坐标移动速度
    private int awardType; //奖励类型(0和1)

    /**
     * 构造方法
     */
    public Bee() {
        super(60, 50);
        xStep = 1; //x移动速度
        yStep = 2; //y移动速度
        Random rand = new Random();
        awardType = rand.nextInt(2); //0到1之间的随机数
    }

    /**
     * 小蜜蜂移动
     */
    public void step() {
        x += xStep; //x+(向左或向右)
        y += yStep; //y+(向下)
        if (x <= 0 || x >= World.WIDTH - this.width) { //蜜蜂飞到两边了
            xStep *= -1; //修改xStep的正负值来实现向左或向右
        }
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
        return this.y >= World.HEIGHT; //小蜜蜂的y>=窗口的高，即为越界了
    }

    /**
     * 重写getType()获取奖励类型
     */
    public int getType() {
        return awardType; //返回奖励类型(0到1之间的随机数)
    }

}













