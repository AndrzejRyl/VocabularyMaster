package com.fleenmobile.vocabularymaster.adding_words;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.adding_words.domain.AddFileTask;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;
import com.fleenmobile.vocabularymaster.statistics.StatisticsPresenter;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;

import javax.inject.Inject;

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
        // TODO
    }

    @Override
    public void addVocabulary(String filePath) {
        // TODO

    }

    @Override
    public void subscribe() {
        mParentPresenter.setAddFileSheetEventListener(listener);
        // TODO

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    private MaterialSheetFabEventListener listener = new MaterialSheetFabEventListener() {
        @Override
        public void onHideSheet() {
            super.onHideSheet();
            mView.onHide();
        }

        @Override
        public void onShowSheet() {
            super.onShowSheet();
            mView.onShow();
        }
    };
}
