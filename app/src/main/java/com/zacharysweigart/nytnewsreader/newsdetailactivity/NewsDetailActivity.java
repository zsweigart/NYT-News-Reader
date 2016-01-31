package com.zacharysweigart.nytnewsreader.newsdetailactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zacharysweigart.nytnewsreader.api.model.NewsFeedStory;

public class NewsDetailActivity extends AppCompatActivity implements NewsDetailController.NewsDetailNavigator {
    private static final String NEWS_STORY = "NEWS_STORY";

    private NewsFeedStory newsFeedStory;
    private NewsDetailController newsDetailController;
    private NewsDetailView newsDetailView;

    public static Intent getNewsDetailIntent(Context context, NewsFeedStory newsFeedStory) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(NEWS_STORY, newsFeedStory);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getIntent().hasExtra(NEWS_STORY)) {
            newsFeedStory = (NewsFeedStory) getIntent().getSerializableExtra(NEWS_STORY);
        }

        newsDetailView = new NewsDetailView(this);
        newsDetailController = new NewsDetailController(this, newsDetailView);
        newsDetailView.setNewsDetailController(newsDetailController);
        newsDetailController.loadNewsStory(newsFeedStory);

        setContentView(newsDetailView);
    }
}
