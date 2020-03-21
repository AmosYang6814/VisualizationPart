package com.example.administrator.visualizationpart;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import static java.security.AccessController.getContext;

public class AttributeAdapter extends ArrayAdapter<AdapterData> {

    private int resourceId;

    public AttributeAdapter(Context context, int textViewResourceId, List<AdapterData> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdapterData adapterData = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView methodName=view.findViewById(R.id.method_text);
        EditText editText=view.findViewById(R.id.method_parameter);

        methodName.setText(adapterData.methodName);
        adapterData.Data=editText.getText().toString();

        return view;
    }

}

