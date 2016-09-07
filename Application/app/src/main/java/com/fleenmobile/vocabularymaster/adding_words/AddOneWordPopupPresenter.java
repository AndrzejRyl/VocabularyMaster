package com.fleenmobile.vocabularymaster.adding_words;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.adding_words.domain.AddOneWordTask;

import rx.subscriptions.CompositeSubscription;

/**
 * @author FleenMobile at 2016-09-07
 */
public class AddOneWordPopupPresenter implements AddOneWordPopupContract.Presenter {

    @NonNull
    private AddOneWordPopupContract.View mView;
    @NonNull
    private AddOneWordTask mTask;
    @NonNull
    CompositeSubscription mSubscriptions;

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
