package com.fleenmobile.vocabularymaster.adding_words;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.adding_words.domain.AddOneWordTask;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * @author FleenMobile at 2016-09-07
 */
public class AddOneVocabularyPopupPresenter implements AddOneVocabularyPopupContract.Presenter {

    @NonNull
    private AddOneVocabularyPopupContract.View mView;
    @NonNull
    private AddOneWordTask mTask;
    @NonNull
    private CompositeSubscription mSubscriptions;
    @NonNull
    private VocabularyDataSource mDataSource;

    @Inject
    AddOneVocabularyPopupPresenter(VocabularyDataSource dataSource, AddOneVocabularyPopupContract.View view) {
        mView = view;
        mDataSource = dataSource;
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void validateFields(String word, String translation) {
        // TODO
    }

    @Override
    public void addVocabulary(String word, String translation) {

        // TODO
    }

    @Override
    public void subscribe() {

        // TODO
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
