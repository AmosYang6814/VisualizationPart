package com.example.administrator.visualizationpart;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.view.View;

//动态组件中的背景板
public class DrawView extends View {

    ConstraintLayout.LayoutParams layoutParams=null;
    int weight=0,hight=0;
    //采用装饰者方法
    View Object;

    //构造方法
    public DrawView(Context context) {
        super(context);
        layoutParams=new ConstraintLayout.LayoutParams(weight,hight);
    }

    public void test(){
        this.setBackgroundColor(Color.BLUE);
        this.setLayoutParams(layoutParams);
    }


}
