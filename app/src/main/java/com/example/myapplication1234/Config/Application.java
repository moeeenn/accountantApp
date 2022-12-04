package com.example.myapplication1234.Config;

import android.content.Context;

public class Application extends android.app.Application {

    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

    }

    public static Context getContext()
    {
        return context;
    }

}
