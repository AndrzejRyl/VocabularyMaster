package com.fleenmobile.vocabularymaster.removing_words.domain;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;

import java.util.List;

import rx.Observable;

/**
 *
 * This task removes vocabulary provided by presenter from the DB
 *
 * @author FleenMobile at 2016-09-09
 */
public class RemoveVocabularyTask {
    @NonNull
    private VocabularyDataSource mRepository;
    @NonNull
    private List<Vocabulary> mVocabularyToRemove;

    public RemoveVocabularyTask(List<Vocabulary> vocabulary) {
        mVocabularyToRemove = vocabulary;
    }

    public Observable<List<Vocabulary>> execute() {
        // TODO
        return null;
    }
}
