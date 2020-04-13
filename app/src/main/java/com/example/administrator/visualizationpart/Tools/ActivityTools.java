package com.example.administrator.visualizationpart.Tools;

import android.content.Context;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ActivityTools {

    public static View CreateView(Context context, Class currentClass){
        try {
            Constructor constructor=currentClass.getConstructor(Context.class);
            View view=(View) constructor.newInstance(context);
            return view;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
