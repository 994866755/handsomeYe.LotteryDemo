package com.example.kylin.lotterydemo.lottery;

import java.util.Random;

/**
 *  管理随机数
 */
public class RandomManager {

    public static float getRandomF(){
        Random random = new Random();
        return random.nextFloat();
    }

    public static int getRandomI(){
        Random random = new Random();
        return random.nextInt();
    }

    public static int getRandomI(int k){
        Random random = new Random();
        return random.nextInt(k);
    }

    /**
     * 设置概率返回所属范围
     * @param probabilities 概率数组
     * @return 返回属于哪个概率的下标
     */
    public static int getIndexFormProbability(float[] probabilities){
        //判断数组所有数累加书否等于1，如果总计不为100%，则返回-1
        float count = 0;
        for (int i = 0; i < probabilities.length; i++) {
            count += probabilities[i];
        }
        if (count != (float) 1){
            return -1;
        }

        //获取随机数
        Random random = new Random();
        float result = random.nextFloat();

        // 把 1 分成很多段，然后判断最后的数会落在哪一段
        count = 0;
        for (int i = 0; i < probabilities.length; i++) {
            count += probabilities[i];
            if (result < count){
               return i;
            }
        }

        return -1;
    }

}
