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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.administrator.visualizationpart.Tools.ActivityTools;
import com.example.administrator.visualizationpart.testData.testManaActivity;
import com.google.gson.Gson;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.AttachListPopupView;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lxj.xpopup.interfaces.XPopupCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import GlobalTools.DataBean.Action.Action;
import GlobalTools.DataBean.Action.ActionMean;
import GlobalTools.DataBean.Action.EventType;
import GlobalTools.DataBean.Attribute;
import GlobalTools.DataBean.UiComponent;
import GlobalTools.GlobalManager;
import UI.ComponentIndex.AbstractDataManager;
import UI.ComponentIndex.DisplayComponent;
import UI.ComponentIndex.componentListinterface;
import UI.Draw.Draw;
import UI.Draw.Size;
import UI.EvenHanding.ActionPopMenu;
import UI.EvenHanding.BuilderAction;
import UI.EvenHanding.EvenPopMenu;
import UI.UICenterCtrol.UIGlobalManager;

public class MainActivity extends AppCompatActivity implements  Draw<View>,componentListinterface<ExpandableListView> , DisplayComponent , ActionPopMenu<View>, EvenPopMenu<View>, BuilderAction<View> {
    public final static int FLAG_DRAW=101;
    public final static int SET_ATTRIBUTE=1102;

    private final MainActivityLogic mainActivityLogic=new MainActivityLogic();
    /**
     * 当前Id为测试id
     */
    public final int screenId= 101; //注册屏幕的id
    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("参数追踪",msg.getData().getString("childName"));

            //testDraw();
           setClickToListPopMenu(componentList,msg.getData().getString("childName"),msg.getData().getString("groupName"));
        }
    };

    View backgroundPanel;
    ExpandableListView componentList;
    ConstraintLayout componentListPanel;
    ConstraintLayout mainConstraintLayout;
    ImageButton componentflodButton;

    //动态组件添加
    LinkedList<UiComponent> dymicsViews=new LinkedList<>();

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
        group.addAll(UIGlobalManager.getDataManager().getAllClassfy());

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

        //注册事件的拖动
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

               EventTag moveTag=( EventTag) view.getTag();
                double x=event.getRawX();
                double y=event.getRawY();
                Log.d("参数追踪", "onTouch: "+event.getAction());
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    moveTag.latsX=x;
                    moveTag.lastY=y;
                }else if (event.getAction()==MotionEvent.ACTION_MOVE){
                    double dx=x-moveTag.latsX;
                    double dy=y-moveTag.lastY;
                    Log.d("参数追踪", "onTouch: dx=="+dx+",dy=="+dy);
//            startAnimation(dx,dy);

                    //  moveMethod1(dx, dy);
                    moveMethod2(view,dx, dy);

                    moveTag.latsX=x;
                    moveTag.lastY=y;
                }
                return false;
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventTag eventTag=(EventTag) v.getTag();
                Log.d("双击追踪", ""+eventTag.lastClickTime);
                if(System.currentTimeMillis()-eventTag.lastClickTime>1000) eventTag.lastClickTime=System.currentTimeMillis();
                else {
                    ClickToInvokeEvenPopMenu(v);
                }
            }
        });

    }




    /**
     *------------------------------------------------------------------------------------------------------------------------------------
     * 绘制动态组件的方法
     */
    @Override
    public Size getScreenSize() {
        return null;
    }


    /**
     * 绘制动态组件
     * @param simpleComponent
     * @return
     */
    @Override
    public boolean drawInscreen(UiComponent simpleComponent) {
        try {

            simpleComponent=mainActivityLogic.InitAndSetAttribute(this,simpleComponent);

            View newView= (View)simpleComponent.getComponentObj();
            simpleComponent.setComponentObj(newView);
            ConstraintLayout.LayoutParams layoutParams=new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.topToTop=mainConstraintLayout.getId();
            layoutParams.leftToLeft=mainConstraintLayout.getId();
            layoutParams.rightToRight=mainConstraintLayout.getId();
            layoutParams.topMargin=500;

            registerViewToch(newView);
            mainConstraintLayout.addView(newView,layoutParams);

            newView.setTag(new EventTag());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 组件移动的注册方法
     * @param view
     */
    @Override
    public void MoveComponent(View view) {


    }


    @Override
    public boolean refresh() {
        return false;
    }

    //根据属性动画的原理
    private void moveMethod2(View v,double dx, double dy) {

        v.setTranslationX((float) (v.getTranslationX()+dx));
        v.setTranslationY((float) (v.getTranslationY()+dy));
    }


    /**
     * 点击弹出属性菜单选项
     * @param object
     */
    @Override
    public void setClickToListPopMenu(ExpandableListView object,String... name) {
        Intent intent=new Intent(this,AttributeSettingPage.class);
        intent.putExtra("groupName",name[1]);
        intent.putExtra("childName",name[0]);

        startActivityForResult(intent,SET_ATTRIBUTE);
    }


    /**
     * 界面跳转结果接收处理,并绘制图形
     * * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case SET_ATTRIBUTE:
                if(resultCode==AttributeSettingPage.RESULT_SUCCESS){
                    LoadData(data);
                }
        }
    }



    private void LoadData(Intent data){

        if(GlobalApplication.Debug){
            Log.i("MainActivity_laodData","得到回传参数");
        }
        ArrayList<String> attributeString=data.getStringArrayListExtra("attribute");

        ArrayList<Attribute> attributes=new ArrayList<>();
        Gson g=new Gson();

        for(int i=0;i<attributeString.size();i++){
            attributes.add(g.fromJson(attributeString.get(i),Attribute.class));
        }

        UiComponent newView=AbstractDataManager.getAbstractDataManager().findComponentById(data.getIntExtra("componentid",0));

        newView.clearAttributes();

        for(Attribute attribute:attributes){
            newView.addDefineAttribute(attribute);
        }
        drawInscreen(newView);
    }

    /**
     * ------------------------------------------------------------------------------------------------------------------------------------------------------------
     * 添加动作事件
     */


    private AttachListPopupView attachListPopupView=null;
    @Override
    public void AttachActionPopMenu(View view,int eventPosition) {
        XPopup.Builder builder=new XPopup.Builder(MainActivity.this).atView(view);
        builder.asAttachList(ActionMean.getSeletionDisplay(), null,-100,0, new OnSelectListener() {
            @Override
            public void onSelect(int position, String text) {
                Log.i("悬浮菜单点击追踪",text);
            }
        }).show();
    }

    @Override
    public void ClickToInvokeEvenPopMenu(final View target) {

            XPopup.Builder builder=new XPopup.Builder(MainActivity.this).atView(target);
             attachListPopupView=builder.asAttachList(EventType.getSelectionDisplay(), null,-100,0, new OnSelectListener() {
                @Override
                public void onSelect(int position, String text) {
                    Log.i("悬浮菜单点击追踪",text);
                    AttachActionPopMenu(target,position);
                }
            });
            attachListPopupView.show();
    }

    /**
     * 构造并添加动作
     * @param view
     * @param eventPosition
     * @param actionPosition
     */
    public void builderandAddAction(View view,int eventPosition,int actionPosition){
        EventTag eventTag=(EventTag) view.getTag();
        Action action=new Action(screenId,eventTag.componentId,EventType.getActionTypeByIndex(eventPosition),ActionMean.getActionMeanByIndex(actionPosition));
        eventTag.actions.add(action);
        UIGlobalManager.getEvenManager().addAction(screenId,action);
    }
    /**
     * ----------------------------------------------------------------------------------------------------------------------------------------------------------
     */
}

class EventTag{
    int componentId=0;
    public double latsX,lastY;
    public long lastClickTime=0;
    ArrayList<Action> actions=new ArrayList<>(4);
}




