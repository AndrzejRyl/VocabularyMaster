package com.fleenmobile.vocabularymaster.learning.domain;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;

import java.util.List;

import rx.Observable;

/**
 *
 * Loads random unlearnt vocabulary from the db
 *
 * @author FleenMobile at 2016-09-07
 */
public class GetRandomVocabularyTask {

    private static final int AMOUNT = 10;

    @NonNull
    private VocabularyDataSource mRepository;


    public Observable<List<Vocabulary>> execute() {
        // TODO
        return null;
    }
}
