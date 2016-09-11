package com.fleenmobile.vocabularymaster.application.di;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * @author FleenMobile at 2016-09-10
 */
@Module
public class AppModule {

    private final Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationScope
    public Application provideApplication() { return mApplication;}
}
