package com.fleenmobile.vocabularymaster.data.di;

import android.app.Application;

import com.fleenmobile.vocabularymaster.application.di.ApplicationScope;
import com.fleenmobile.vocabularymaster.data.source.LocalVocabularyDataSource;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;

import dagger.Module;
import dagger.Provides;

/**
 * @author FleenMobile at 2016-09-12
 */
@Module
public class VocabularyDataSourceModule {

    @Provides
    @ApplicationScope
    public VocabularyDataSource provideVocabularyDataSource(Application application) {
        return new LocalVocabularyDataSource(application);
    }
}
