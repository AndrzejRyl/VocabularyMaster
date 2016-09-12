package com.fleenmobile.vocabularymaster.application;

import android.app.Application;

import com.fleenmobile.vocabularymaster.application.di.AnalyticsModule;
import com.fleenmobile.vocabularymaster.application.di.AppModule;
import com.fleenmobile.vocabularymaster.data.di.DaggerDataComponent;
import com.fleenmobile.vocabularymaster.data.di.DataComponent;
import com.fleenmobile.vocabularymaster.data.di.DataModule;

/**
 * @author FleenMobile at 2016-09-05
 */
public class VocabularyApplication extends Application {

    private DataComponent mDataComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mDataComponent = DaggerDataComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule())
                .analyticsModule(new AnalyticsModule())
                .build();
    }

    public DataComponent getDataComponent() {
        return mDataComponent;
    }
}
