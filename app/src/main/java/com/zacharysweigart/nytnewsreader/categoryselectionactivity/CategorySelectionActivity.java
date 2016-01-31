package com.zacharysweigart.nytnewsreader.categoryselectionactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CategorySelectionActivity extends AppCompatActivity implements CategorySelectionController.CategorySelectionNavigator {
    private static final String NEWS_STORY = "NEWS_STORY";

    private CategorySelectionView categorySelectionView;
    private CategorySelectionController categorySelectionController;

    public static Intent getCategorySelectionIntent(Context context) {
        Intent intent = new Intent(context, CategorySelectionActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        categorySelectionView = new CategorySelectionView(this);
        categorySelectionController = new CategorySelectionController(this, categorySelectionView);
        categorySelectionView.setCategorySelectionController(categorySelectionController);
        categorySelectionView.createCategorySelectionRadioGroup();

        setContentView(categorySelectionView);
    }
}
