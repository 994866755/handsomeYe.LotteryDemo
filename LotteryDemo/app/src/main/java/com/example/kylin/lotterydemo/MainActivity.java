package com.example.kylin.lotterydemo;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kylin.lotterydemo.lottery.AdminHelper;
import com.example.kylin.lotterydemo.lottery.DiskEntity;
import com.example.kylin.lotterydemo.lottery.DiskView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<DiskEntity> disks = new ArrayList<>();
    private DiskView diskView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        diskView = new DiskView.DiskViewBuilder()
                .setRadius(400)
                .setDiskList(disks)
                .builder(this);
        LinearLayout ll = findViewById(R.id.ll_content);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        diskView.setLayoutParams(lp);
        ll.addView(diskView);

        TextView textView = findViewById(R.id.tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminHelper.showLottery(diskView);
            }
        });
    }

    private void initData(){
        DiskEntity d1 = new DiskEntity();
        d1.textContent = "d1";
        d1.bgColor = getResources().getColor(R.color.oneColor);
        d1.iconBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        disks.add(d1);

        DiskEntity d2 = new DiskEntity();
        d2.textContent = "d2";
        d2.bgColor =getResources().getColor(R.color.twoColor);
        d2.iconBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        disks.add(d2);

        DiskEntity d3 = new DiskEntity();
        d3.textContent = "d3";
        d3.bgColor = getResources().getColor(R.color.oneColor);
        d3.iconBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        disks.add(d3);

        DiskEntity d4 = new DiskEntity();
        d4.textContent = "d4";
        d4.bgColor = getResources().getColor(R.color.twoColor);
        d4.iconBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        disks.add(d4);

        DiskEntity d5 = new DiskEntity();
        d5.textContent = "d5";
        d5.bgColor = getResources().getColor(R.color.oneColor);
        d5.iconBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        disks.add(d5);

        DiskEntity d6 = new DiskEntity();
        d6.textContent = "d6";
        d6.bgColor = getResources().getColor(R.color.twoColor);
        d6.iconBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round);
        disks.add(d6);
    }


}
