package com.zacharysweigart.nytnewsreader.newsfeedactivity;

import com.zacharysweigart.nytnewsreader.api.model.NewsFeedStories;

import java.util.ArrayList;

import rx.Subscriber;

public class NewsFeedSubscriber extends Subscriber<NewsFeedStories> {
    private NewsFeedView newsFeedView;

    public NewsFeedSubscriber(NewsFeedView newsFeedView) {
        super();
        this.newsFeedView = newsFeedView;
    }

    @Override
    public void onCompleted() {
        newsFeedView.hideProgress();
    }

    @Override
    public void onError(Throwable e) {
        newsFeedView.hideProgress();
    }

    @Override
    public void onNext(NewsFeedStories newsFeedStories) {
        newsFeedView.setNewsFeedStories(newsFeedStories.getNewFeedStoryList());
    }
}
