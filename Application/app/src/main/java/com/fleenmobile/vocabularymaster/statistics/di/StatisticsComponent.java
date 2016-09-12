package com.fleenmobile.vocabularymaster.statistics.di;

import com.fleenmobile.vocabularymaster.MainActivity;
import com.fleenmobile.vocabularymaster.adding_words.di.AddingWordsModule;
import com.fleenmobile.vocabularymaster.data.di.DataComponent;

import dagger.Component;

/**
 * @author FleenMobile at 2016-09-12
 */
@StatisticsScope
@Component(dependencies = {DataComponent.class}, modules = {StatisticsModule.class, AddingWordsModule.class})
public interface StatisticsComponent {

    void inject(MainActivity mainActivity);
}
