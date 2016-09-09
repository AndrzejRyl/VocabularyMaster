package com.fleenmobile.vocabularymaster.removing_words;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;

import rx.subscriptions.CompositeSubscription;

/**
 * @author FleenMobile at 2016-09-09
 */
public class RemoveVocabularyConfirmationPresenter implements RemovingVocabularyContract.Presenter {

    @NonNull
    private RemoveVocabularyConfirmationPopupContract.View mView;
    @NonNull
    private RemovingVocabularyContract.Presenter mParentPresenter;

    @NonNull
    private CompositeSubscription mSubscriptions;


    @Override
    public void loadVocabulary() {
        // TODO
    }

    @Override
    public void removeVocabulary() {
        // TODO
    }

    @Override
    public void onVocabularyChosen(Vocabulary vocabulary) {
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
