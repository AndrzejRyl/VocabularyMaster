package com.fleenmobile.vocabularymaster.statistics.di;

import com.fleenmobile.vocabularymaster.statistics.StatisticsContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author FleenMobile at 2016-09-12
 */
@Module
public class StatisticsModule {

    private StatisticsContract.View mView;

    public StatisticsModule(StatisticsContract.View view) {
        mView = view;
    }

    @Provides
    public StatisticsContract.View provideStatisticsView() { return mView;}
}
