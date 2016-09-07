package com.fleenmobile.vocabularymaster.revision.domain;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;

import java.util.List;

import rx.Observable;

/**
 *
 * Loads learnt vocabulary from DB based on correct tries percentage
 *
 * @author FleenMobile at 2016-09-07
 */
public class GetVocabularyToReviseTask {
    private static final int AMOUNT = 10;

    @NonNull
    private VocabularyDataSource mRepository;

    public Observable<List<Vocabulary>> execute() {
        // TODO
        return null;
    }
}
