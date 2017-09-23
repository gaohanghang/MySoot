package cn.ghang.shoot;

/**
 * 奖励接口
 */
public interface Award {
    public int DOUBLE_FIRE = 0; //火力值
    public int LIFE = 1; //命

    /**
     * 获取奖励类型（0到1）
     */
    public int getType();
}
