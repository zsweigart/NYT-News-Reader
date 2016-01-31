package com.zacharysweigart.nytnewsreader.newsdetailactivity;

import com.zacharysweigart.nytnewsreader.api.model.Multimedia;
import com.zacharysweigart.nytnewsreader.api.model.NewsFeedStory;

import java.util.List;

public class NewsDetailController {
    private NewsDetailView newsDetailView;
    private NewsDetailNavigator newsDetailNavigator;

    public NewsDetailController(NewsDetailNavigator newsDetailNavigator, NewsDetailView newsDetailView) {
        this.newsDetailView = newsDetailView;
        this.newsDetailNavigator = newsDetailNavigator;
    }

    public void loadNewsStory(NewsFeedStory newsFeedStory) {
        List<Multimedia> multimediaList = newsFeedStory.getMultimedia();
        if(!multimediaList.isEmpty()) {
            Multimedia multimedia = multimediaList.get(multimediaList.size() - 1);
            newsDetailView.setDetailImage(multimedia.url);
            newsDetailView.setCopyright(multimedia.copyright);
        } else {
            newsDetailView.setDetailImage(null);
            newsDetailView.setCopyright(null);
        }

        newsDetailView.setHeadline(newsFeedStory.getTitle());
        newsDetailView.setAbstract(newsFeedStory.getAbstractString());
        newsDetailView.setArticleUrl(newsFeedStory.getUrl());
        newsDetailView.setByline(newsFeedStory.getByline());

        newsDetailView.setActionBar(newsFeedStory.getSection());
    }

    public interface NewsDetailNavigator {

    }
}
