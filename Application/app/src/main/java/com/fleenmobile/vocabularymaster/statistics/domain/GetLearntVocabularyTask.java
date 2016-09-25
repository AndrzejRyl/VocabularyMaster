package com.fleenmobile.vocabularymaster.statistics.domain;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Loads vocabulary that has been learnt by the user
 *
 * @author FleenMobile at 2016-09-07
 */
public class GetLearntVocabularyTask {
    @NonNull
    private VocabularyDataSource mRepository;

    private int mAmount;
    private int mOffset;

    public GetLearntVocabularyTask(@NonNull VocabularyDataSource mRepository, int mAmount, int mOffset) {
        this.mRepository = mRepository;
        this.mAmount = mAmount;
        this.mOffset = mOffset;
    }

    public Observable<List<Vocabulary>> execute() {
        return mRepository.getLearntVocabulary(mAmount, mOffset)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
