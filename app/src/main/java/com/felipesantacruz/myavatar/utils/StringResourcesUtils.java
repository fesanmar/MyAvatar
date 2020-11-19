package com.felipesantacruz.myavatar.utils;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.felipesantacruz.myavatar.App;

import java.util.Locale;

public class StringResourcesUtils {
    private static Resources res = App.getContext().getResources();
    private static Configuration conf;
    private static Locale savedLocale;

    public static String getResStringLanguage(int id, String lang) {
        catchDefaultLocale();
        Resources resources = getResourcesFor(lang);
        String string = resources.getString(id);
        restoreDefaultLocale();
        return string;
    }

    private static void catchDefaultLocale() {
        conf = res.getConfiguration();
        savedLocale = conf.locale;
    }

    public static Resources getResourcesFor(String lang) {
        Configuration confAr = res.getConfiguration();
        confAr.locale = new Locale(lang);
        DisplayMetrics metrics = new DisplayMetrics();
        return new Resources(res.getAssets(), metrics, confAr);
    }

    private static void restoreDefaultLocale() {
        conf.locale = savedLocale;
        res.updateConfiguration(conf, null);
    }

}
