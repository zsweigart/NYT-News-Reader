package com.zacharysweigart.nytnewsreader.categoryselectionactivity;

import com.zacharysweigart.newscard.Category;
import com.zacharysweigart.nytnewsreader.NytNewsReaderApp;
import com.zacharysweigart.nytnewsreader.persistence.NytNewsReaderSharedPreferences;

import javax.inject.Inject;

public class CategorySelectionController {
    @Inject
    NytNewsReaderSharedPreferences nytNewsReaderSharedPreferences;

    private CategorySelectionView categorySelectionView;
    private CategorySelectionNavigator categorySelectionNavigator;

    public CategorySelectionController(CategorySelectionNavigator categorySelectionNavigator, CategorySelectionView categorySelectionView) {
        this.categorySelectionView = categorySelectionView;
        this.categorySelectionNavigator = categorySelectionNavigator;

        NytNewsReaderApp.inject(this);
    }

    public int getCategorySelectionPreferenceLocation() {
        String categoryPreferenceString = nytNewsReaderSharedPreferences.getCategoryPreference();
        for(int i = 0; i < Category.values().length; i++) {
            Category category = Category.values()[i];
            if(categoryPreferenceString.equalsIgnoreCase(category.getUrlString())) {
                return i;
            }
        }

        return 0;
    }

    public void setCategorySelectionPreference(Category category) {
        nytNewsReaderSharedPreferences.setCategoryPreference(category.getUrlString());
    }

    public interface CategorySelectionNavigator {

    }
}
