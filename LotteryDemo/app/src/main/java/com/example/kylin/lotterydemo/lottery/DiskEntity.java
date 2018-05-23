package com.example.kylin.lotterydemo.lottery;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 *  每个扇形对象的数据
 */
public class DiskEntity {
    // 背景颜色
    public int bgColor = -1;
    // 文字内容
    public String textContent;
    // Icon
    public Bitmap iconBitmap;
    // 字体颜色
    public int textColor = Color.BLACK;
    // 子体尺寸
    public int textSize = 36;

}
