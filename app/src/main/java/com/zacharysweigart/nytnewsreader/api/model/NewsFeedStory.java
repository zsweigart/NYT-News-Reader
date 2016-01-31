package com.zacharysweigart.nytnewsreader.api.model;

import android.text.Html;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class NewsFeedStory implements Serializable {
    String section;
    String title;
    @SerializedName("abstract")
    String abstractString;
    String url;
    String byline;
    String thumbnail_standard;
    ArrayList<Multimedia> multimedia;

    public NewsFeedStory(String section, String title, String abstractString, String url, String byline, String thumbnail_standard, ArrayList<Multimedia> multimedia) {
        this.section = section;
        this.title = title;
        this.abstractString = abstractString;
        this.url = url;
        this.byline = byline;
        this.thumbnail_standard = thumbnail_standard;
        this.multimedia = multimedia;
    }

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return Html.fromHtml(title).toString();
    }

    public String getAbstractString() {
        return Html.fromHtml(abstractString).toString();
    }

    public String getUrl() {
        return url;
    }

    public String getByline() {
        return byline;
    }

    public String getThumbnail_standard() {
        return thumbnail_standard;
    }

    public ArrayList<Multimedia> getMultimedia() {
        return multimedia;
    }
}
