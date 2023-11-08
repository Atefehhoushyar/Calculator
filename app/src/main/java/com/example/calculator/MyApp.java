package com.example.calculator;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {

    public ArrayList<String> getHistoryList() {
        if(historyList==null){
            historyList = new ArrayList<>(0);
        }
        return historyList;
    }

    ArrayList<String> historyList;


}
