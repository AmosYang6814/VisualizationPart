package com.example.administrator.visualizationpart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//属性设置页
public class AttributeSettingPage extends Activity {
    public static final int RESULT_SUCCESS=10002;
    public static final int RESULT_FIRARE=10003;

    ArrayList<AdapterData> list=new ArrayList<>();
    Button commit,cancel;
    ListView listView;
    AttributeAdapter attributeAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attribute_setting_page);

        Intent intent=getIntent();

       list=(ArrayList<AdapterData>) intent.getSerializableExtra("methodData");

       initView();
       setEventListener();
    }

    private void initView(){
        commit=findViewById(R.id.commitButton);
        cancel=findViewById(R.id.cancelButton);

        attributeAdapter=new AttributeAdapter(this,R.layout.attribute_setting_list_view,list);
        listView=findViewById(R.id.AttributeList);
        listView.setAdapter(attributeAdapter);
    }

    private void setEventListener(){
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putSerializable("methodData",list);

                intent.putExtra("data",bundle);
                setResult(RESULT_SUCCESS,intent);

                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_FIRARE);
                finish();
            }
        });
    }
}
