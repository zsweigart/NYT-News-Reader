package com.zacharysweigart.nytnewsreader.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.zacharysweigart.newscard.Category;
import com.zacharysweigart.nytnewsreader.R;

import javax.inject.Inject;

public class NytNewsReaderSharedPreferences {
    private static final String CATEGORY_PREFERENCE = "CATEGORY_PREFERENCE";

    private Context context;
    private SharedPreferences sharedPreferences;

    @Inject
    public NytNewsReaderSharedPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.nyt_shared_preferences), Context.MODE_PRIVATE);
    }

    public void setCategoryPreference(String categoryPreference) {
        sharedPreferences.edit().putString(CATEGORY_PREFERENCE, categoryPreference).commit();
    }

    public String getCategoryPreference() {
        return sharedPreferences.getString(CATEGORY_PREFERENCE, Category.MISCELLANEOUS.getUrlString());
    }
}
