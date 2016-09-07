package com.fleenmobile.vocabularymaster.statistics.domain;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;

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

    private int mAmount;
    private int mOffset;

    public GetWorstKnownVocabularyTask(@NonNull VocabularyDataSource mRepository, int mAmount, int mOffset) {
        this.mRepository = mRepository;
        this.mAmount = mAmount;
        this.mOffset = mOffset;
    }

    public Observable<List<Vocabulary>> execute() {
        // TODO
        return null;
    }
}
