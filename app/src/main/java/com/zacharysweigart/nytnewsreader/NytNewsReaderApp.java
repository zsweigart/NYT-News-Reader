package com.zacharysweigart.nytnewsreader;

import android.app.Application;
import android.content.Context;

import com.zacharysweigart.nytnewsreader.api.ApiModule;
import com.zacharysweigart.nytnewsreader.dagger.RootModule;

import dagger.ObjectGraph;

public class NytNewsReaderApp extends Application {
    protected static Context context;
    protected ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        injectDependencies();
    }

    protected void injectDependencies() {
        objectGraph = ObjectGraph.create(new RootModule(this), new ApiModule());
        objectGraph.inject(this);
    }

    public static void inject(Object object) {
        ((NytNewsReaderApp) context).objectGraph.inject(object);
    }
}
