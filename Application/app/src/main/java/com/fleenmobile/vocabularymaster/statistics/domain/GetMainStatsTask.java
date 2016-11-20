package com.fleenmobile.vocabularymaster.statistics.domain;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.model.Stats;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;
import com.fleenmobile.vocabularymaster.utils.schedulers.BaseSchedulerProvider;

import rx.Observable;

/**
 * Loads main statistics from the db like amount of all words or amount of all learnt words
 *
 * @author FleenMobile at 2016-09-07
 */
public class GetMainStatsTask {
    @NonNull
    private VocabularyDataSource mRepository;
    @NonNull BaseSchedulerProvider mSchedulerProvider;

    public GetMainStatsTask(VocabularyDataSource repository, BaseSchedulerProvider provider) {
        mRepository = repository;
        mSchedulerProvider = provider;
    }

    public Observable<Stats> execute() {
        return mRepository.getStats()
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui());
    }
}
