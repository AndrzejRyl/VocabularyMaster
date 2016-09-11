package com.fleenmobile.vocabularymaster.data.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fleenmobile.vocabularymaster.BuildConfig;
import com.fleenmobile.vocabularymaster.application.di.ApplicationScope;
import com.fleenmobile.vocabularymaster.data.FakeVocabularyDataSource;
import com.fleenmobile.vocabularymaster.data.source.LocalVocabularyDataSource;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * @author FleenMobile at 2016-09-10
 */
@Module
public class DataModule {

    @Provides
    @ApplicationScope
    public SharedPreferences provideSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @ApplicationScope
    public VocabularyDataSource provideVocabularyDataSource(Application application) {
        return (BuildConfig.FLAVOR.equals("mock")) ? new FakeVocabularyDataSource(application) : new LocalVocabularyDataSource(application);
    }
}
