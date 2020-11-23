package com.felipesantacruz.myavatar.avatar;

import static com.felipesantacruz.myavatar.utils.StringResourcesUtils.getResStringLanguage;

public class Profession {
    private String name;
    private int resourceId;

    public Profession(int resourceId) {
        this.name = getResStringLanguage(resourceId, "en");
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }
}
