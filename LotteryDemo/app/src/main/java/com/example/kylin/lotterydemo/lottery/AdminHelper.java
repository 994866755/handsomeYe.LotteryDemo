package com.example.kylin.lotterydemo.lottery;

import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 *  动画帮助类
 */
public class AdminHelper {

    /**
     * 转盘动画
     * 必定转3圈，然后再随机转0—4圈，第二个随机数表示最后停止的位置
     */
    public static void showLottery(View view){
        int n = RandomManager.getRandomI(4);
        int k = RandomManager.getRandomI((int) (360f));

        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "rotation", 0f,k+(3+n)*360f);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setDuration(5000);
        anim.start();
    }

}
