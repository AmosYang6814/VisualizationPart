package com.example.administrator.visualizationpart;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import GlobalTools.DataBean.UiComponent;
import UI.ComponentIndex.AbstractDataManager;
import UI.ComponentIndex.componentListinterface;
import UI.Draw.Draw;
import UI.Draw.Size;

public class MainActivity extends AppCompatActivity implements componentListinterface, Draw {
    public final static int FLAG_DRAW=101;

    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Log.i("参数追踪",msg.getData().getString("name"));
            testDraw();
        }
    };

    View backgroundPanel;


    ExpandableListView componentList;
    ConstraintLayout componentListPanel;
    ConstraintLayout mainConstraintLayout;
    ImageButton componentflodButton;

    //动态组件添加
    LinkedList<View> DymicsViews=new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*添加附加数据*/
        Intent intent=new Intent();
        intent.putExtra("handler",new Gson().toJson(handler));
        this.setIntent(intent);

        initView();/*初始化控件*/
        EventResiger();/*事件注册*/

    }


    /**
     * 初始化方法
     * */
    private void initView(){
        backgroundPanel=findViewById(R.id.backgroundPanel);
        mainConstraintLayout=findViewById(R.id.main_screen);

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
        MyAdapter myAdapter=new MyAdapter(handler,this,groupTemp,childtemp);

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

        /**
         * 点击菜单显示点击的选项
         */
        componentList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {


                Log.i("点击子菜单","参数："+"groupPosition:"+groupPosition+",childPosition:"+childPosition+",id:"+id);
                return false;
            }
        });
    }

    /**
     * 注册添加的组件的长按监听方法
     */

    private void registerViewToch(final View view){
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                    Log.i("点击追踪","Action:"+event.getAction()+"Time:"+event.getEventTime());

                    if(event.getAction()==MotionEvent.ACTION_DOWN){
                        v.setTag(new ActionTag(MotionEvent.ACTION_DOWN,event.getEventTime()));
                    }else if(event.getAction()==MotionEvent.ACTION_MOVE && ((ActionTag)v.getTag()).getStates()==MotionEvent.ACTION_DOWN){

                        //移动代码
                        ConstraintLayout.LayoutParams layoutParams=(ConstraintLayout.LayoutParams) v.getLayoutParams();

                    }else if(event.getAction()==MotionEvent.ACTION_UP){
                        v.setTag(new ActionTag(MotionEvent.ACTION_UP,event.getEventTime()));
                    }
                    if(event.getAction()==MotionEvent.ACTION_DOWN && ((ActionTag)v.getTag()).getStates()==
                            MotionEvent.ACTION_UP && event.getEventTime()-((ActionTag)v.getTag()).getTime()<1000){

                        //选定大小放缩代码
                    }

                return false;
            }
        });
    }

    /**
     *------------------------------------------------------------------------------------------------------------------------------------
     * 绘制方法
     */
    @Override
    public Size getScreenSize() {
        return null;
    }

    @Override
    public boolean drawInscreen(List<Object> simpleComponent) {
        return false;
    }

    @Override
    public boolean drawComplexScreen(List<ArrayList<Object>> complexComponent) {
        return false;
    }

    @Override
    public boolean SetAttribute(Object object, String attribute, Object value) {
        return false;
    }

    @Override
    public boolean refresh() {
        return false;
    }

    /**
     * 测试绘制方法
     */
    public void testDraw(){

        Button new1=new Button(MainActivity.this);
        new1.setText("测试");
        ConstraintLayout.LayoutParams layoutParams=new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.topToTop=mainConstraintLayout.getId();
        layoutParams.leftToLeft=mainConstraintLayout.getId();
        layoutParams.rightToRight=mainConstraintLayout.getId();
        layoutParams.topMargin=500;

        registerViewToch(new1);
        mainConstraintLayout.addView(new1,layoutParams);

    }


}

class ActionTag{
    int state;
    long time;
    ActionTag(int state,long Time){
        this.state=state;
        this.time=Time;
    }
    public int getStates(){return state;}
    public long getTime(){return time;}
}




