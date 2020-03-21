package com.example.administrator.visualizationpart.testData;

import com.example.administrator.visualizationpart.AdapterData;

import java.util.ArrayList;
import java.util.List;

public class testManaActivity {


    //测试获取属性数据
   public static ArrayList<AdapterData> getComponentData(){
       ArrayList<AdapterData> adapterDatas=new ArrayList<>();

       for(int  i=0;i<10;i++){
           AdapterData adapterData=new AdapterData();
           adapterData.methodName="test"+i;
           adapterDatas.add(adapterData);
       }
       return adapterDatas;
   }
}
