package com.fleenmobile.vocabularymaster.adding_words.domain;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.model.Translation;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;
import com.google.common.collect.Lists;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This task adds one vocabulary to database
 *
 * @author FleenMobile at 2016-09-07
 */
public class AddOneWordTask {
    @NonNull
    private VocabularyDataSource mRepository;
    private String wordText;
    private String translationText;

    public AddOneWordTask(String word, String translation, VocabularyDataSource repository) {
        this.wordText = checkNotNull(word, "Word is null!");
        this.translationText = checkNotNull(translation, "Translation is null!");
        this.mRepository = repository;
    }

    public Observable<Vocabulary> execute() {
        Translation translation = new Translation(translationText, false, 0, 0);

        return mRepository.addVocabulary(new Vocabulary(wordText, Lists.newArrayList(translation)))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
