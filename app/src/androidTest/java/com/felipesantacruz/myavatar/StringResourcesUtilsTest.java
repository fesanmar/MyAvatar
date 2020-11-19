package com.felipesantacruz.myavatar;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.felipesantacruz.myavatar.utils.StringResourcesUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class StringResourcesUtilsTest {
    private static final String DWARF_SPANISH = "enano";
    private static final String DWARF_ENGLISH = "dwarf";
    private static final String SPANISH_CODE = "es";
    private static final String ENGLISH_CODE = "en";
    private static Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    @Test
    public void testFetchingASpanishStringResourceFromEnglishContext() {
        String sutOutput = StringResourcesUtils.getResStringLanguage(R.string.race_dwarf, SPANISH_CODE);
        assertEquals(DWARF_SPANISH, sutOutput);
    }

    @Test
    public void testFetchingAEnglishStringResourceFromSpanighContext() {
        StringResourcesUtils.getResourcesFor(SPANISH_CODE);
        String sutOutput = StringResourcesUtils.getResStringLanguage(R.string.race_dwarf, ENGLISH_CODE);
        assertEquals(DWARF_ENGLISH, sutOutput);
    }
}