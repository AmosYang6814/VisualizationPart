package com.example.administrator.visualizationpart;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import GlobalTools.DataBean.Attribute;

import static java.security.AccessController.getContext;

public class AttributeAdapter extends ArrayAdapter<Attribute> {

    private int resourceId;
    protected HashMap<Attribute,Integer> map=new HashMap<>();

    public HashMap<Attribute, Integer> getMap() {
        return map;
    }
    public AttributeAdapter(Context context, int textViewResourceId, List<Attribute> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }


    //获取坐标
    public int getPosition(Attribute attribute){
        return map.get(attribute);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Attribute adapterData = getItem(position);

        if(GlobalApplication.Debug){
            Log.i("Attribute日志","----------------------------");
            Log.i("Attribute日志",adapterData.getName());
            Log.i("Attribute日志",adapterData.getReflectMethod());
            Log.i("Attribute日志",adapterData.getNumber()+"");
            Log.i("Attribute日志",Logger.Tools.PrintArray(adapterData.getTypes()));
            Log.i("Attribute日志",Logger.Tools.PrintArray(adapterData.getValues()));
            Log.i("Attribute日志","----------------------------");
        }


        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView methodName=view.findViewById(R.id.method_text);
        EditText editText=view.findViewById(R.id.method_parameter);
        methodName.setText(adapterData.getName());

        map.put(adapterData,position);

        return view;
    }

}

