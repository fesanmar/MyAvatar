package com.felipesantacruz.myavatar;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Context myAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        myAppContext = this;
    }

    public static Context getContext(){
        return myAppContext;
    }
}
