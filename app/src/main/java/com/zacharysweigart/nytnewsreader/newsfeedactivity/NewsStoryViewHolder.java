package com.zacharysweigart.nytnewsreader.newsfeedactivity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.zacharysweigart.newscard.NewsCard;
import com.zacharysweigart.nytnewsreader.api.model.Multimedia;
import com.zacharysweigart.nytnewsreader.api.model.NewsFeedStory;
import com.zacharysweigart.nytnewsreader.newsdetailactivity.NewsDetailController;

import java.io.IOException;
import java.util.List;

public class NewsStoryViewHolder extends RecyclerView.ViewHolder {
    NewsCard newsCard;
    Context context;

    public NewsStoryViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        newsCard = (NewsCard) itemView;
    }

    public void bindData(final NewsFeedStory newsFeedStory, final NewsFeedController controller) {
        newsCard.setNewsCategory(newsFeedStory.getSection());
        newsCard.setNewsHeadline(newsFeedStory.getTitle());
        newsCard.setNewsByline(newsFeedStory.getByline());


        List<Multimedia> multimediaList = newsFeedStory.getMultimedia();
        if(!multimediaList.isEmpty()) {
            Multimedia multimedia = multimediaList.get(multimediaList.size() - 1);
            newsCard.setNewsImage(multimedia.url);
        } else{
            newsCard.setNewsImage(null);
        }

        newsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.openDetail(newsFeedStory);
            }
        });
    }
}
