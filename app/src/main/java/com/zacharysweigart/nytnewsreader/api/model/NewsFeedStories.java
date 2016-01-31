package com.zacharysweigart.nytnewsreader.api.model;

import java.util.ArrayList;

public class NewsFeedStories {

    public ArrayList<NewsFeedStory> results;

    public ArrayList<NewsFeedStory> getNewFeedStoryList() {
        return results;
    }
}
