package com.example.administrator.visualizationpart;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.ListView;

//属性设置页
public class AttributeSettingPage extends Activity {
    Button commit,cancel;
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attribute_setting_page);

    }

    private void initView(){
        commit=findViewById(R.id.commitButton);
        cancel=findViewById(R.id.cancelButton);
        listView=findViewById(R.id.AttributeList);
    }


}
