package com.example.kylin.lotterydemo.lottery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.kylin.lotterydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 *  圆盘控件
 */
public class DiskView extends View{

    private int widthMode;
    private int widthSize;
    private int heightMode;
    private int heightSize;
    private float cX;
    private float cY;
    private DiskViewBuilder builder;

    private Paint mPaint;//饼状画笔
    private Paint textPaint;// 文字画笔
    private Paint divisionPaint; //分割线画笔

    public DiskView(Context context) {
        super(context);
        init();
    }

    public DiskView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DiskView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBuilder(DiskViewBuilder builder){
        this.builder = builder;
        cX = builder.radius;
        cY = builder.radius;
    }

    private void init(){
        mPaint = new Paint();
        textPaint = new Paint();
        divisionPaint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthMode = MeasureSpec.getMode(widthMeasureSpec);
        widthSize = MeasureSpec.getSize(widthMeasureSpec);
        heightMode = MeasureSpec.getMode(heightMeasureSpec);
        heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (builder != null) {
            if (builder.radius != 0) {
                setMeasuredDimension(2 * builder.radius, 2 * builder.radius);
            } else {
                setMeasuredDimension(widthSize, heightSize);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (builder != null) {
            // 判断是否有扇形
            if (builder.disks == null || builder.disks.size() <= 0){
                return;
            }

            // 设置扇形画笔
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setStrokeWidth(2);
            mPaint.setAntiAlias(true);
            // 设置分割线画笔
            if (builder.division > 0) {
                divisionPaint.setStyle(Paint.Style.FILL);
                divisionPaint.setStrokeWidth(builder.division);
                divisionPaint.setColor(builder.divisionColor);
            }

            RectF f = new RectF(0, 0, 2 * builder.radius, 2 * builder.radius);
            for (int i = 0; i < builder.disks.size(); i++) {

                //设置每个扇形的文字
                textPaint.setColor(builder.disks.get(i).textColor);
                textPaint.setTextSize(builder.disks.get(i).textSize);

                // 设置每个扇形的颜色
                if (builder.disks.get(i).bgColor != -1){
                    mPaint.setColor(builder.disks.get(i).bgColor);
                }else {
                    mPaint.setColor(Color.WHITE);
                }

                // 设置绘制图片的区域
                Rect rect = null;
                if (builder.picLocations != null && builder.picLocations.length > 0){
                    rect = new Rect(builder.picLocations[0],builder.picLocations[1],builder.picLocations[2],builder.picLocations[3]);
                }else {
                    // 设为默认位置
                    // todo 计算默认方位有错，自行修改
                    rect = new Rect((int) (cX + builder.radius / 3),
                            (int)(cY + Math.sin(180/builder.disks.size() * Math.PI / 180) + 50),
                            (int) (cX + builder.radius / 3 + 150),
                            (int)(cY + Math.sin(180/builder.disks.size() * Math.PI / 180) + 200));
                }

                // 设置每个扇形的角度
                if (builder.angles != null && builder.angles.length > 0){
                    // 绘制扇形区域
                    canvas.drawArc(f, 0, builder.angles[i], true, mPaint);
                    // 绘制分割线
                    if (builder.division > 0) {
                        canvas.drawLine(cX, cY, cX + builder.radius, cY, divisionPaint);
                    }
                    // 绘制图标
                    if (builder.disks.get(i).iconBitmap != null) {
                        canvas.drawBitmap(builder.disks.get(i).iconBitmap, null, rect, null);
                    }
                    // 绘制文字
                    canvas.drawText(builder.disks.get(i).textContent,
                            (float) (cX + Math.cos(builder.angles[i]/2 * Math.PI / 180) * (builder.radius-36)),
                            (float) (cY + Math.sin(builder.angles[i]/2 * Math.PI / 180) * (builder.radius-36)),
                            textPaint);
                    //旋转画布
                    canvas.rotate(builder.angles[i], cX, cY);
                }else {
                    canvas.drawArc(f, 0, 360/builder.disks.size(), true, mPaint);
                    if (builder.division > 0) {
                        canvas.drawLine(cX, cY, cX + builder.radius, cY, divisionPaint);
                    }
                    if (builder.disks.get(i).iconBitmap != null) {
                        canvas.drawBitmap(builder.disks.get(i).iconBitmap, null, rect, null);
                    }
                    canvas.drawText(builder.disks.get(i).textContent,
                            (float) (cX + Math.cos(180/builder.disks.size() * Math.PI / 180) * (builder.radius-36)),
                            (float) (cY + Math.sin(180/builder.disks.size() * Math.PI / 180) * (builder.radius-36)),
                            textPaint);
                    canvas.rotate(360/builder.disks.size(), cX, cY);
                }


            }

        }

    }

    public static class DiskViewBuilder{
        private int radius = 0;
        private List<DiskEntity> disks = new ArrayList<>();
        private int[] angles;
        private int[] probabilities;
        private int[] picLocations;
        private int division = 0;
        private int divisionColor = -1;

        /**
         * 设置半径
         */
        public DiskViewBuilder setRadius(int radius){
            this.radius = radius;
            return this;
        }
        /**
         *  设置扇形对象数组
         */
        public DiskViewBuilder setDiskList(List<DiskEntity> disks){
            this.disks = disks;
            return this;
        }
        /**
         * 设置角度的数组
         */
        public DiskViewBuilder setAngles(int[] angles){
            this.angles = angles;
            return this;
        }

        /**
         * 设置概率数组
         */
        public DiskViewBuilder setProbabilities(int[] probabilities){
            this.probabilities = probabilities;
            return this;
        }

        /**
         * 设置图片的距离
         */
        public DiskViewBuilder setPicLocations(int[] picLocations){
            this.picLocations = picLocations;
            return this;
        }

        /**
         * 设置分割线
         */
        public DiskViewBuilder setDivision(int division,int divisionColor){
            this.division = division;
            this.divisionColor = divisionColor;
            return this;
        }

        public DiskView builder(Context context){
            DiskView diskView = new DiskView(context);
            diskView.setBuilder(this);
            return diskView;
        }

    }



}
