package com.zacharysweigart.nytnewsreader.dagger;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.zacharysweigart.nytnewsreader.NytNewsReaderApp;
import com.zacharysweigart.nytnewsreader.api.ApiModule;
import com.zacharysweigart.nytnewsreader.api.model.Multimedia;
import com.zacharysweigart.nytnewsreader.api.model.NewsFeedStory;
import com.zacharysweigart.nytnewsreader.categoryselectionactivity.CategorySelectionController;
import com.zacharysweigart.nytnewsreader.newsfeedactivity.NewsFeedController;
import com.zacharysweigart.nytnewsreader.persistence.NytNewsReaderSharedPreferences;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (
        includes = {
                ApiModule.class,
        },
        injects = {
                NytNewsReaderApp.class,
                NewsFeedController.class,
                CategorySelectionController.class,
        },
        library = true
)
public class RootModule {
    private final NytNewsReaderApp nytNewsReaderApp;

    public RootModule(NytNewsReaderApp nytNewsReaderApp) {
        this.nytNewsReaderApp = nytNewsReaderApp;
    }

    @Provides
    @Singleton
    public NytNewsReaderApp provideNytNewsReaderApp() {
        return nytNewsReaderApp;
    }

    @Provides
    @Singleton
    public NytNewsReaderSharedPreferences provideSharedPreferences(NytNewsReaderApp nytNewsReaderApp) {
        return new NytNewsReaderSharedPreferences(nytNewsReaderApp);
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(NewsFeedStory.class, new JsonDeserializer<NewsFeedStory>() {
                    @Override
                    public NewsFeedStory deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        JsonObject jObj = json.getAsJsonObject();
                        JsonElement jElement = jObj.get("multimedia");
                        ArrayList<Multimedia> multimedias = Lists.newArrayList();
                        if(jElement.isJsonArray()) {
                            multimedias = context.deserialize(jElement.getAsJsonArray(), new TypeToken<List<Multimedia>>(){}.getType());
                        }
                        //assuming there is an appropriate constructor
                        return new NewsFeedStory(jObj.getAsJsonPrimitive("section").getAsString(),
                                jObj.getAsJsonPrimitive("title").getAsString(),
                                jObj.getAsJsonPrimitive("abstract").getAsString(),
                                jObj.getAsJsonPrimitive("url").getAsString(),
                                jObj.getAsJsonPrimitive("byline").getAsString(),
                                jObj.getAsJsonPrimitive("thumbnail_standard").getAsString(),
                                multimedias);
                    }
                })
                .create();
    }
}
