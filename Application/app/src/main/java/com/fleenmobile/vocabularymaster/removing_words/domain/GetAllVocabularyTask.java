package com.fleenmobile.vocabularymaster.removing_words.domain;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;

import java.util.List;

import rx.Observable;

/**
 *
 * This task loads all vocabulary available in DB.
 *
 * @author FleenMobile at 2016-09-09
 */
public class GetAllVocabularyTask {
    @NonNull
    private VocabularyDataSource mRepository;

    public Observable<List<Vocabulary>> execute() {
        // TODO
        return null;
    }
}
