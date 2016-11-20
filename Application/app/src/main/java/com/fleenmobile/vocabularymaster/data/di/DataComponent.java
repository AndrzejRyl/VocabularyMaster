package com.fleenmobile.vocabularymaster.data.di;

import android.content.SharedPreferences;

import com.fleenmobile.vocabularymaster.application.di.AppModule;
import com.fleenmobile.vocabularymaster.application.di.ApplicationScope;
import com.fleenmobile.vocabularymaster.application.di.AnalyticsModule;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;
import com.fleenmobile.vocabularymaster.utils.GoogleAnalyticsHelper;
import com.fleenmobile.vocabularymaster.utils.LogWrapper;
import com.fleenmobile.vocabularymaster.utils.schedulers.BaseSchedulerProvider;

import dagger.Component;

/**
 * @author FleenMobile at 2016-09-10
 */
@Component(modules = {AppModule.class, DataModule.class, AnalyticsModule.class})
@ApplicationScope
public interface DataComponent {

    VocabularyDataSource getVocabularyDataSource();
    SharedPreferences getSharedPreferences();
    LogWrapper getLogWrapper();
    GoogleAnalyticsHelper getAnalyticsHelper();
    BaseSchedulerProvider getSchedulerProvider();
}
