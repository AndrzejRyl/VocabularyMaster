package com.fleenmobile.vocabularymaster.removing_words;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * @author FleenMobile at 2016-09-09
 */
public class RemoveVocabularyConfirmationPresenter implements RemoveVocabularyConfirmationPopupContract.Presenter {

    @NonNull
    private RemoveVocabularyConfirmationPopupContract.View mView;
    @NonNull
    private RemovingVocabularyContract.Presenter mParentPresenter;

    @NonNull
    private CompositeSubscription mSubscriptions;

    @NonNull
    private VocabularyDataSource mDataSource;

    @Inject
    RemoveVocabularyConfirmationPresenter(RemoveVocabularyConfirmationPopupContract.View view, VocabularyDataSource dataSource) {
        mView = view;
        mDataSource = dataSource;

        mSubscriptions = new CompositeSubscription();
    }

    @Inject
    public void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void onConfirmed() {
        // TODO
    }

    @Override
    public void onCancelled() {
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
