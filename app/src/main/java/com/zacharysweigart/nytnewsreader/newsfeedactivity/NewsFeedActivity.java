package com.zacharysweigart.nytnewsreader.newsfeedactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.zacharysweigart.nytnewsreader.R;
import com.zacharysweigart.nytnewsreader.api.model.NewsFeedStory;
import com.zacharysweigart.nytnewsreader.categoryselectionactivity.CategorySelectionActivity;
import com.zacharysweigart.nytnewsreader.newsdetailactivity.NewsDetailActivity;

public class NewsFeedActivity extends AppCompatActivity implements NewsFeedController.NewsFeedNavigator {
    private static final String NEWS_FEED_SCROLL = "NEWS_FEED_SCROLL";
    private static final int CATEGORY_SELECTION = 1;

    private NewsFeedView newsFeedView;
    private NewsFeedController newsFeedController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newsFeedView = new NewsFeedView(this, this);
        newsFeedController = new NewsFeedController(this, newsFeedView);
        newsFeedView.setNewsFeedController(newsFeedController);
        if(savedInstanceState == null) {
            newsFeedController.loadNewsStories();
        } else {
            newsFeedView.setScrollPosition(savedInstanceState.getInt(NEWS_FEED_SCROLL));
        }

        setContentView(newsFeedView);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(NEWS_FEED_SCROLL, newsFeedView.getScrollPosition());

        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_feed_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_categories:
                openCategories();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        newsFeedController.unsubscribe();
    }

    @Override
    public String getApiKey() {
        return getResources().getString(R.string.nyt_newswire_api_key);
    }

    @Override
    public void openDetail(NewsFeedStory newsFeedStory) {
        Intent intent = NewsDetailActivity.getNewsDetailIntent(this, newsFeedStory);
        startActivity(intent);
    }

    @Override
    public void openCategories() {
        Intent intent = CategorySelectionActivity.getCategorySelectionIntent(this);
        startActivityForResult(intent, CATEGORY_SELECTION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CATEGORY_SELECTION) {
            newsFeedController.loadNewsStories();
        }
    }
}
