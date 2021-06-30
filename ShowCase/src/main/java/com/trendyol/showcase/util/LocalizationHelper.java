package com.trendyol.showcase.util;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;

import java.util.Locale;

public class LocalizationHelper {
    public final static String LOCALE_ARABIC = "ar";
    public final static String LOCALE_ENGLISH = "en";
    private static final String PREF_LOCALE = "PREF_LOCALE";

    public static Context onAttach(Context context) {

        String lang = getCurrentLocale(context);
        return setLocale(context, lang);
    }

    public static void switchLocale(Context context) {

        String nLang;
        if (getCurrentLocale(context).equals(LOCALE_ARABIC)) {
            nLang = LOCALE_ENGLISH;
        } else {
            nLang = LOCALE_ARABIC;
        }

        SharedPreferences sp = getSharedPreferences(context);
        sp.edit().putString(PREF_LOCALE, nLang).apply();
    }


    public static String getCurrentLocale(Context context) {

        SharedPreferences sp = getSharedPreferences(context);
        return sp.getString(PREF_LOCALE,/*Default Localization*/ LOCALE_ARABIC);
    }

    private static SharedPreferences getSharedPreferences(final Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @SuppressLint("NewApi")
    private static Context setLocale(Context context, String langCode) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();

        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);
        return context.createConfigurationContext(configuration);
    }
    
    public static void setActivityLocal(Context context,Window window){
        Locale myLocale;
        String lang = LocalizationHelper.getCurrentLocale(context);
        if (lang.equalsIgnoreCase(LocalizationHelper.LOCALE_ARABIC)) {
            myLocale = new Locale(LocalizationHelper.LOCALE_ARABIC);
            window.getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        } else {
            myLocale = new Locale(LocalizationHelper.LOCALE_ENGLISH);
            window.getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

        Locale.setDefault(myLocale);
        Configuration config = new Configuration();
        config.locale = myLocale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }
}