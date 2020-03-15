package com.example.administrator.visualizationpart;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

import GlobalTools.DataBean.UiComponent;
import UI.ComponentIndex.AbstractDataManager;
import UI.ComponentIndex.componentListinterface;

public class MainActivity extends AppCompatActivity implements componentListinterface {

    View backgroundPanel;


    ExpandableListView componentList;
    ConstraintLayout componentListPanel;
    ImageButton componentflodButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();/*初始化控件*/

        EventResiger();/*事件注册*/

    }

    /**
     * 初始化方法
     * */
    private void initView(){
        backgroundPanel=findViewById(R.id.backgroundPanel);

        componentList=findViewById(R.id.component_list);
        componentList.setVisibility(View.INVISIBLE);

        componentListPanel=findViewById(R.id.component_list_panel);
        componentListPanel.setBackgroundColor(Color.WHITE);
        componentListPanel.setVisibility(View.INVISIBLE);

        componentflodButton=findViewById(R.id.component_flod_button);
    }


    /**
     * 显示所有组件
     */
    @Override
    public void displayAllComponent() {
        if(componentList==null )return;

        if(componentList.getAdapter()!=null){
            componentList.setVisibility(View.VISIBLE);
            componentListPanel.setVisibility(View.VISIBLE);
            componentList.deferNotifyDataSetChanged();
        }
        LinkedList<UiComponent> result= AbstractDataManager.getAbstractDataManager().getAllcomponent();



        /*按照标签进行分类*/
        HashSet<String> group=new HashSet<>();
        for(UiComponent uiComponent:result)group.add(uiComponent.getClassfiy());

        /*获取各个分类，及其子项目*/
        HashMap<String,ArrayList<String>> hashMap=new HashMap();
        for(String s:group)hashMap.put(s,new ArrayList<String>());
        for(UiComponent uiComponent:result){
            ArrayList arrayList=hashMap.get(uiComponent.getClassfiy());
            arrayList.add(uiComponent.getNearName());
        }
        ArrayList<String> groupTemp=new ArrayList<>();
        groupTemp.addAll(group);

        ArrayList<ArrayList<String>> childtemp=new ArrayList<>();
        for(Map.Entry<String,ArrayList<String>> entry:hashMap.entrySet()){
            childtemp.add(entry.getValue());
        }
        MyAdapter myAdapter=new MyAdapter(this,groupTemp,childtemp);

        componentList.setAdapter(myAdapter);
        componentList.setVisibility(View.VISIBLE);
        componentListPanel.setVisibility(View.VISIBLE);
    }

    /**
     * 事件注册方法
     */
    private void EventResiger(){
        componentflodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("流程追踪","点击了打开按钮");
                displayAllComponent();
            }
        });

        componentList.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i("流程追踪","componentList焦点切换："+hasFocus);
                if(!hasFocus){
                    componentList.setVisibility(View.INVISIBLE);
                    componentListPanel.setVisibility(View.INVISIBLE);
                }
            }
        });

        /**
         * 点击背景板隐藏组件菜单
         */
        backgroundPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("流程追踪","背景板获取焦点");
                backgroundPanel.setFocusable(true);
                componentList.setFocusable(false);
                componentList.setVisibility(View.INVISIBLE);
                componentListPanel.setVisibility(View.INVISIBLE);
            }
        });
    }
}
