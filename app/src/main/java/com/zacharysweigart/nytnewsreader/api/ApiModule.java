package com.zacharysweigart.nytnewsreader.api;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

@Module (
        library = true,
        complete = false
)
public class ApiModule {
    @Provides
    @Singleton
    public NytApi provideNytApi(Gson gson) {
        return getRestAdapter(gson).create(NytApi.class);
    }

    private RestAdapter getRestAdapter(Gson gson) {
        return new RestAdapter.Builder()
                .setEndpoint("http://api.nytimes.com/svc/news/v3/content")
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }
}
