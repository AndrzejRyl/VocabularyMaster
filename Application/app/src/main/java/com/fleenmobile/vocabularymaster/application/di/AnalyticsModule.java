package com.fleenmobile.vocabularymaster.application.di;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.fleenmobile.vocabularymaster.BuildConfig;
import com.fleenmobile.vocabularymaster.utils.LogWrapper;

import dagger.Module;
import dagger.Provides;
import io.fabric.sdk.android.Fabric;

/**
 * @author FleenMobile at 2016-09-12
 */
@Module
public class AnalyticsModule {

    @Provides
    @ApplicationScope
    public LogWrapper provideLogWrapper(Application application) {
        // Init crashlytics
        CrashlyticsCore core = new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build();
        Fabric.with(application, new Crashlytics.Builder().core(core).build());

        return new LogWrapper(application);
    }
}
