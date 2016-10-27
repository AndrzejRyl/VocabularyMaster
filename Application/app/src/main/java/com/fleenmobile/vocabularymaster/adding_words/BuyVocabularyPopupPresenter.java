package com.fleenmobile.vocabularymaster.adding_words;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.BuyVocabularyPopupContract;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * @author FleenMobile at 2016-09-26
 */

public class BuyVocabularyPopupPresenter implements BuyVocabularyPopupContract.Presenter {
    @NonNull
    private BuyVocabularyPopupContract.View mView;
    @NonNull
    private CompositeSubscription mSubscriptions;
    @NonNull
    private VocabularyDataSource mDataSource;

    @Inject
    public BuyVocabularyPopupPresenter(VocabularyDataSource dataSource, BuyVocabularyPopupContract.View view) {
        mView = view;
        mDataSource = dataSource;

        mSubscriptions = new CompositeSubscription();
    }


    @Override
    public void subscribe() {
        prepareInAppPurchases();
    }

    private void prepareInAppPurchases() {
        setupInAppBilling();
        downloadItemPrice();
    }

    private void setupInAppBilling() {
        // TODO:
        // !Check if already bought!
    }

    private void downloadItemPrice() {
        // TODO
        String price = "";
        mView.setPrice(price);
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void buy() {
        // TODO
    }
}
