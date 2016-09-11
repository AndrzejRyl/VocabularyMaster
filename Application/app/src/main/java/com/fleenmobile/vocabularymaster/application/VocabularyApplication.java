package com.fleenmobile.vocabularymaster.application;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.fleenmobile.vocabularymaster.BuildConfig;
import com.fleenmobile.vocabularymaster.application.di.AppModule;
import com.fleenmobile.vocabularymaster.data.di.DaggerDataComponent;
import com.fleenmobile.vocabularymaster.data.di.DataComponent;
import com.fleenmobile.vocabularymaster.data.di.DataModule;

import io.fabric.sdk.android.Fabric;

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
                .build();

        CrashlyticsCore core = new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build();
        Fabric.with(this, new Crashlytics.Builder().core(core).build());
    }

    public DataComponent getDataComponent() {
        return mDataComponent;
    }
}
