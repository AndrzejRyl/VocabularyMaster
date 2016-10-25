package com.fleenmobile.vocabularymaster.adding_words;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.adding_words.domain.AddFileTask;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;
import com.fleenmobile.vocabularymaster.statistics.StatisticsPresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author FleenMobile at 2016-09-07
 */
public class AddFilePopupPresenter implements AddFilePopupContract.Presenter {

    @NonNull
    private AddFilePopupContract.View mView;
    @NonNull
    private AddFileTask mTask;
    /**
     * This is a presenter of a parent view. It's needed in order to receive
     * fileChooser callback
     */
    @NonNull
    private StatisticsPresenter mParentPresenter;
    @NonNull
    private CompositeSubscription mSubscriptions;
    @NonNull
    private VocabularyDataSource mDataSource;

    @Inject
    AddFilePopupPresenter(VocabularyDataSource dataSource, AddFilePopupContract.View view, StatisticsPresenter parentPresenter) {
        mView = view;
        mDataSource = dataSource;
        mParentPresenter = parentPresenter;

        mSubscriptions = new CompositeSubscription();
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void showFileChooser() {
        mView.showFileChooser();
    }

    @Override
    public void addVocabulary(Uri uri, Context context) {
        mView.onProgress();

        // Without that thread Android won't return from FileChooser before finishing this subscription
        new Thread(() -> {
            try {
                Subscription subscription = new AddFileTask(mDataSource, uri, context)
                        .execute()
                        .subscribe(
                                vocabularyList -> {
                                    if (mView.isActive())
                                        mView.onSuccess(vocabularyList.size());
                                },
                                error -> {
                                    if (mView.isActive())
                                        mView.onError();
                                });

                mSubscriptions.add(subscription);
            } catch (Exception e) {
                mView.onError();
            }
        }).start();
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
