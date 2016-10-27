package com.fleenmobile.vocabularymaster.statistics;

import android.support.annotation.NonNull;
import android.view.View;

import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;
import com.fleenmobile.vocabularymaster.statistics.domain.GetMainStatsTask;
import com.fleenmobile.vocabularymaster.statistics.domain.GetTopKnownVocabularyTask;
import com.fleenmobile.vocabularymaster.statistics.domain.GetWorstKnownVocabularyTask;
import com.fleenmobile.vocabularymaster.utils.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author FleenMobile at 2016-09-07
 */
public class StatisticsPresenter implements StatisticsContract.Presenter {

    private static final int INITIAL_LOAD_AMOUNT = 4;

    @NonNull
    private StatisticsContract.View mView;
    @NonNull
    private CompositeSubscription mSubscriptions;
    @NonNull
    private VocabularyDataSource mDataSource;
    @NonNull
    BaseSchedulerProvider mSchedulerProvider;

    private boolean mFABExpanded = false;

    @Inject
    StatisticsPresenter(VocabularyDataSource dataSource, StatisticsContract.View view, BaseSchedulerProvider provider) {
        mView = view;
        mDataSource = dataSource;
        mSchedulerProvider = provider;

        mSubscriptions = new CompositeSubscription();
    }

    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void loadMainStats() {
        if (mView.isActive())
            mView.onLoadingMainStats();

        Subscription subscription =
                new GetMainStatsTask(mDataSource, mSchedulerProvider)
                        .execute()
                        .subscribe(stats -> {
                            if (mView.isActive())
                                mView.onLoadedMainStats(stats);
                        });

        mSubscriptions.add(subscription);
    }

    @Override
    public void loadTopKnownVocabulary(int amount, int offset) {
        if (mView.isActive())
            mView.onLoadingTopKnownVocabulary();

        Subscription subscription =
                new GetTopKnownVocabularyTask(mDataSource, mSchedulerProvider, amount, offset)
                        .execute()
                        .subscribe(vocabulary -> {
                            if (mView.isActive())
                                mView.onLoadedTopKnownVocabulary(vocabulary);
                        });

        mSubscriptions.add(subscription);
    }

    @Override
    public void loadWorstKnownVocabulary(int amount, int offset) {
        if (mView.isActive())
            mView.onLoadingWorstKnownVocabulary();

        Subscription subscription =
                new GetWorstKnownVocabularyTask(mDataSource, mSchedulerProvider, amount, offset)
                        .execute()
                        .subscribe(vocabulary -> {
                            if (mView.isActive())
                                mView.onLoadedWorstKnownVocabulary(vocabulary);
                        });

        mSubscriptions.add(subscription);
    }

    @Override
    public void loadFileChooser() {
        // TODO
    }

    @Override
    public void onFabMenu(View v) {
        if (mFABExpanded) {
            mView.collapseFAB();
        } else {
            mView.expandFAB();
        }

        mFABExpanded = !mFABExpanded;
    }

    @Override
    public void subscribe() {
        loadMainStats();
        loadTopKnownVocabulary(INITIAL_LOAD_AMOUNT, 0);
        loadWorstKnownVocabulary(INITIAL_LOAD_AMOUNT, 0);
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
