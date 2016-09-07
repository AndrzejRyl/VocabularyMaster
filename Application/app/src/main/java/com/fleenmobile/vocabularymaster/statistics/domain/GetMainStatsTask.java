package com.fleenmobile.vocabularymaster.statistics.domain;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.model.Stats;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;

import rx.Observable;

/**
 *
 * Loads main statistics from the db like amount of all words or amount of all learnt words
 *
 * @author FleenMobile at 2016-09-07
 */
public class GetMainStatsTask {
    @NonNull
    private VocabularyDataSource mRepository;

    public Observable<Stats> execute() {
        // TODO
        return null;
    }
}
