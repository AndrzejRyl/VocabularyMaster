package com.fleenmobile.vocabularymaster.adding_words.domain;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.adding_words.VocabularyFileParser;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;

import java.util.List;

import rx.Observable;

/**
 *
 * This task adds vocabulary from the file to the database
 *
 * @author FleenMobile at 2016-09-07
 */
public class AddFileTask {
    @NonNull
    private VocabularyDataSource mRepository;
    private VocabularyFileParser mParser;

    public Observable<List<Vocabulary>> execute() {
        // TODO
        return null;
    }
}
