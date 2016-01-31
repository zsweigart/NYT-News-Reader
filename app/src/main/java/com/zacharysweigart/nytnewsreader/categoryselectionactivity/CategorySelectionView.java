package com.zacharysweigart.nytnewsreader.categoryselectionactivity;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.zacharysweigart.newscard.Category;
import com.zacharysweigart.nytnewsreader.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategorySelectionView extends ScrollView {
    private Context context;
    private CategorySelectionController categorySelectionController;

    @Bind(R.id.categories_radio_group)
    RadioGroup categoriesRadioGroup;

    public CategorySelectionView(Context context) {
        super(context);
        this.context = context;

        inflate(context, R.layout.category_selection_view, this);

        ButterKnife.bind(this);

        ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void setCategorySelectionController(CategorySelectionController categorySelectionController) {
        this.categorySelectionController = categorySelectionController;
    }

    public void createCategorySelectionRadioGroup() {
        categoriesRadioGroup.setOrientation(RadioGroup.VERTICAL);
        int selectedLocation = categorySelectionController.getCategorySelectionPreferenceLocation();
        for(int i = 0; i < Category.values().length; i++) {
            RadioButton radioButton  = new RadioButton(context);
            radioButton.setId(i);
            Category category = Category.values()[i];
            String radioButtonText = category == Category.MISCELLANEOUS ? "All" : category.getCategoryTitle();
            radioButton.setText(radioButtonText);
            if(i == selectedLocation) {
                radioButton.setChecked(true);
            }

            categoriesRadioGroup.addView(radioButton);
        }

        categoriesRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Category category = Category.values()[checkedId];
                categorySelectionController.setCategorySelectionPreference(category);
            }
        });
    }
}
