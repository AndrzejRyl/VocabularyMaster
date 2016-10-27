package com.fleenmobile.vocabularymaster.data.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fleenmobile.vocabularymaster.application.di.ApplicationScope;
import com.fleenmobile.vocabularymaster.utils.schedulers.BaseSchedulerProvider;
import com.fleenmobile.vocabularymaster.utils.schedulers.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * @author FleenMobile at 2016-09-10
 */
@Module(
        includes = VocabularyDataSourceModule.class
)
public class DataModule {

    @Provides
    @ApplicationScope
    public SharedPreferences provideSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @ApplicationScope
    public BaseSchedulerProvider provideBaseSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }
}
