package com.zacharysweigart.nytnewsreader.newsfeedactivity;

import com.zacharysweigart.nytnewsreader.NytNewsReaderApp;
import com.zacharysweigart.nytnewsreader.api.NytApi;
import com.zacharysweigart.nytnewsreader.api.model.NewsFeedStory;
import com.zacharysweigart.nytnewsreader.persistence.NytNewsReaderSharedPreferences;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsFeedController {
    @Inject
    NytApi nytApi;
    @Inject
    NytNewsReaderSharedPreferences nytNewsReaderSharedPreferences;

    private NewsFeedNavigator newsFeedNavigator;
    private NewsFeedView newsFeedView;
    private NewsFeedSubscriber newsFeedSubscriber;

    public NewsFeedController(NewsFeedNavigator newsFeedNavigator, NewsFeedView newsFeedView) {
        this.newsFeedNavigator = newsFeedNavigator;
        this.newsFeedView = newsFeedView;

        NytNewsReaderApp.inject(this);
    }

    public void loadNewsStories() {
        newsFeedView.showProgress();

        newsFeedSubscriber = new NewsFeedSubscriber(newsFeedView);
        nytApi.getRecentNews(nytNewsReaderSharedPreferences.getCategoryPreference(), 10, newsFeedNavigator.getApiKey())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(newsFeedSubscriber);
    }

    public void unsubscribe() {
        if(newsFeedSubscriber != null && !newsFeedSubscriber.isUnsubscribed()) {
            newsFeedSubscriber.unsubscribe();
        }
    }

    public void openDetail(NewsFeedStory newsFeedStory) {
        newsFeedNavigator.openDetail(newsFeedStory);
    }

    public interface NewsFeedNavigator {
        String getApiKey();
        void openDetail(NewsFeedStory newsFeedStory);
        void openCategories();
    }
}
