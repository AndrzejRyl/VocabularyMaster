package com.fleenmobile.vocabularymaster.data.di;

import com.fleenmobile.vocabularymaster.application.di.AppModule;
import com.fleenmobile.vocabularymaster.application.di.ApplicationScope;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;

import dagger.Component;

/**
 * @author FleenMobile at 2016-09-10
 */
@Component(modules = {AppModule.class, DataModule.class})
@ApplicationScope
public interface DataComponent {

    public VocabularyDataSource getVocabularyDataSource();
}
