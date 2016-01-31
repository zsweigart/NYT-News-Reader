package com.zacharysweigart.nytnewsreader.newsfeedactivity;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.zacharysweigart.nytnewsreader.R;
import com.zacharysweigart.nytnewsreader.api.model.NewsFeedStory;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsFeedView extends SwipeRefreshLayout {

    private Context context;
    private NewsFeedAdapter newsFeedAdapter;
    private NewsFeedController newsFeedController;

    @Bind(R.id.news_feed_recycler)
    RecyclerView newsRecycler;

    public NewsFeedView(Context context, NewsFeedController.NewsFeedNavigator newsFeedNavigator) {
        super(context);
        this.context = context;

        inflate(context, R.layout.news_feed_view, this);

        ButterKnife.bind(this);

        newsFeedAdapter = new NewsFeedAdapter(context);
        newsRecycler.setAdapter(newsFeedAdapter);
        newsRecycler.setLayoutManager(new LinearLayoutManager(context));

        setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                newsFeedController.loadNewsStories();
            }
        });
    }

    public void setNewsFeedController(NewsFeedController newsFeedController) {
        this.newsFeedController = newsFeedController;
        newsFeedAdapter.setNewsFeedController(newsFeedController);
    }

    public void setNewsFeedStories(List<NewsFeedStory> newsFeedStories) {
        newsFeedAdapter.setNewsFeedStories(newsFeedStories);
        newsFeedAdapter.notifyDataSetChanged();
        newsRecycler.getLayoutManager().scrollToPosition(0);
    }

    public int getScrollPosition() {
        return ((LinearLayoutManager) newsRecycler.getLayoutManager()).findFirstVisibleItemPosition();
    }

    public void setScrollPosition(int position) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) newsRecycler.getLayoutManager();
        if(layoutManager != null){
            int count = layoutManager.getChildCount();
            if(position < count){
                layoutManager.scrollToPosition(position);
            }
        }
    }

    public void showProgress() {
        setRefreshing(true);
    }

    public void hideProgress() {
        setRefreshing(false);
    }
}
