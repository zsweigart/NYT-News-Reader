package com.zacharysweigart.nytnewsreader.newsfeedactivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.zacharysweigart.newscard.NewsCard;
import com.zacharysweigart.nytnewsreader.api.model.NewsFeedStory;

import java.util.ArrayList;
import java.util.List;

public class NewsFeedAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<NewsFeedStory> newsFeedStories;
    private NewsFeedController newsFeedController;

    public NewsFeedAdapter(Context context) {
        super();
        this.context = context;
        newsFeedStories = new ArrayList<>();
    }

    public void setNewsFeedController(NewsFeedController newsFeedController) {
        this.newsFeedController = newsFeedController;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsCard newsCard = new NewsCard(context);
        return new NewsStoryViewHolder(newsCard);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((NewsStoryViewHolder) holder).bindData(newsFeedStories.get(position), newsFeedController);
    }

    @Override
    public int getItemCount() {
        return newsFeedStories.size();
    }

    public void setNewsFeedStories(List<NewsFeedStory> newsFeedStories) {
        this.newsFeedStories = newsFeedStories;
    }
}
