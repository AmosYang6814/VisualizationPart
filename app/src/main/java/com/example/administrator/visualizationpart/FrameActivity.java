package com.example.administrator.visualizationpart;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

public class FrameActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ConstraintLayout left;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);

        initView();
    }

    private void initView(){

        if(GlobalApplication.Debug){
            Log.i("MainActivity","初始化控件");
        }
        drawerLayout=findViewById(R.id.Frame_drawerLayout);
        left=findViewById(R.id.FilePanel);

    }

    private void RegisterEvent(){

    }

    public void display(){
        drawerLayout.openDrawer(Gravity.LEFT);
    }
}
