package com.zacharysweigart.nytnewsreader.api;

import com.zacharysweigart.nytnewsreader.api.model.NewsFeedStories;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface NytApi {

    @GET("/all/{section}/.json")
    Observable<NewsFeedStories> getRecentNews(@Path(value = "section", encode = false) String section, @Query("limit") int limit, @Query("api-key") String apiKey);
}
