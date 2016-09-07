package com.fleenmobile.vocabularymaster.adding_words.domain;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;

import rx.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This task adds one vocabulary to database
 *
 * @author FleenMobile at 2016-09-07
 */
public class AddOneWordTask {
    @NonNull
    private VocabularyDataSource mRepository;
    private String word;
    private String translation;

    public AddOneWordTask(String word, String translation) {
        this.word = checkNotNull(word, "Word is null!");
        this.translation = checkNotNull(translation, "Translation is null!");
    }

    public Observable<Vocabulary> execute() {
        // TODO
        return null;
    }
}
