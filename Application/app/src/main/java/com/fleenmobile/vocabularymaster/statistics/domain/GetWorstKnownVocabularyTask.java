package com.fleenmobile.vocabularymaster.statistics.domain;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.VocabularySortingStrategy;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;
import com.fleenmobile.vocabularymaster.utils.schedulers.BaseSchedulerProvider;

import java.util.List;

import rx.Observable;

/**
 * Loads vocabulary learnt by the user sorted by percentage of correct tries
 *
 * @author FleenMobile at 2016-09-07
 */
public class GetWorstKnownVocabularyTask {
    @NonNull
    private VocabularyDataSource mRepository;
    @NonNull
    private BaseSchedulerProvider mSchedulerProvider;

    private int mAmount;
    private int mOffset;

    public GetWorstKnownVocabularyTask(@NonNull VocabularyDataSource mRepository, BaseSchedulerProvider mSchedulerProvider, int mAmount, int mOffset) {
        this.mRepository = mRepository;
        this.mAmount = mAmount;
        this.mOffset = mOffset;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    public Observable<List<Vocabulary>> execute() {
        return mRepository.getLearntVocabularySortedBy(mAmount, mOffset, VocabularySortingStrategy.SORT_BY_CORRECT_TRIES_PERC)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui());
    }
}
