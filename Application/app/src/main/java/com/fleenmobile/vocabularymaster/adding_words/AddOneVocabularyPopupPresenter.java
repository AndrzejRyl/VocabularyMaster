package com.fleenmobile.vocabularymaster.adding_words;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.adding_words.domain.AddOneWordTask;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;
import com.fleenmobile.vocabularymaster.statistics.StatisticsPresenter;
import com.gordonwong.materialsheetfab.MaterialSheetFabEventListener;

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
        mParentPresenter.setAddOneVocabularySheetEventListener(listener);
        // TODO
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    private MaterialSheetFabEventListener listener = new MaterialSheetFabEventListener() {
        @Override
        public void onSheetHidden() {
            super.onSheetHidden();
            mView.onHide();
        }

        @Override
        public void onSheetShown() {
            super.onSheetShown();
            mView.onShow();
        }
    };
}
