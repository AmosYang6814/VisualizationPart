package com.example.administrator.visualizationpart;

import android.app.Application;

import java.io.IOException;
import java.io.InputStream;

import GlobalTools.GlobalManager;
import GlobalTools.Perproties;

public class GlobalApplication extends Application implements Perproties {

    public static  final  boolean Debug=true;

    @Override
    public void onCreate() {
        super.onCreate();
        registerToGlobalManager();
    }

    /**
     * 将自己注册到GlobalManager中
     */
    @Override
    public void registerToGlobalManager() {
        GlobalManager.registerPerproties(this);
    }

    /**
     * 获取简单模块定义的文件流
     * @return
     */
    @Override
    public InputStream getSimpleComponentDefineFile() {
        try {
            return getResources().getAssets().open("SimpleProperties.xml");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
