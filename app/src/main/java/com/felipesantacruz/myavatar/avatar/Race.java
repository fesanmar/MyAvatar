package com.felipesantacruz.myavatar.avatar;

import android.content.res.Resources;

import static com.felipesantacruz.myavatar.utils.StringResourcesUtils.getResStringLanguage;

import java.util.Locale;

public class Race {
    private String name;
    private int resourceId;
    public Race(int resourceId) {
        this.name = getResStringLanguage(resourceId, "en");
        this.resourceId = resourceId;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return Resources.getSystem().getString(resourceId);
    }
}
