package com.fleenmobile.vocabularymaster.adding_words;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.adding_words.domain.AddOneWordTask;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;
import com.fleenmobile.vocabularymaster.statistics.StatisticsPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author FleenMobile at 2016-09-07
 */
public class AddOneVocabularyPopupPresenter implements AddOneVocabularyPopupContract.Presenter {

    @NonNull
    private AddOneVocabularyPopupContract.View mView;
    @NonNull
    private CompositeSubscription mSubscriptions;
    @NonNull
    private VocabularyDataSource mDataSource;
    @NonNull
    private StatisticsPresenter mParentPresenter;

    @Inject
    AddOneVocabularyPopupPresenter(VocabularyDataSource dataSource, AddOneVocabularyPopupContract.View view, StatisticsPresenter parentPresenter) {
        mView = view;
        mDataSource = dataSource;
        mParentPresenter = parentPresenter;

        mSubscriptions = new CompositeSubscription();
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    public boolean validateFields(String word, String translation) {
        if (word == null || word.equals("")) {
            mView.setWordETError();
            return false;
        }
        if (translation == null || translation.equals("")) {
            mView.setTranslationETError();
            return false;
        }
        return true;
    }

    @Override
    public void addVocabulary(String word, String translation) {
        if (validateFields(word, translation)) {

            Subscription subscription =
                    new AddOneWordTask(word, translation, mDataSource)
                            .execute()
                            .subscribe(
                                    vocabulary -> {
                                        if (mView.isActive())
                                            mView.onSuccess();
                                    },
                                    error -> {
                                        if (mView.isActive())
                                            mView.onError();
                                    });

            mSubscriptions.add(subscription);
        }
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
