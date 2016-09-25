package com.fleenmobile.vocabularymaster.statistics;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;
import com.fleenmobile.vocabularymaster.statistics.domain.GetLearntVocabularyTask;
import com.fleenmobile.vocabularymaster.statistics.domain.GetMainStatsTask;
import com.fleenmobile.vocabularymaster.statistics.domain.GetTopKnownVocabularyTask;
import com.fleenmobile.vocabularymaster.statistics.domain.GetWorstKnownVocabularyTask;

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

    @Inject
    StatisticsPresenter(VocabularyDataSource dataSource, StatisticsContract.View view) {
        mView = view;
        mDataSource = dataSource;

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
                new GetMainStatsTask(mDataSource)
                        .execute()
                        .subscribe(stats -> {
                            if (mView.isActive())
                                mView.onLoadedMainStats(stats);
                        });

        mSubscriptions.add(subscription);
    }

    @Override
    public void loadLearntVocabulary(int amount, int offset) {
        if (mView.isActive())
            mView.onLoadingLearntVocabulary();

        Subscription subscription =
                new GetLearntVocabularyTask(mDataSource, amount, offset)
                        .execute()
                        .subscribe(vocabulary -> {
                            if (mView.isActive())
                                mView.onLoadedLearntVocabulary(vocabulary);
                        });

        mSubscriptions.add(subscription);
    }

    @Override
    public void loadTopKnownVocabulary(int amount, int offset) {
        if (mView.isActive())
            mView.onLoadingTopKnownVocabulary();

        Subscription subscription =
                new GetTopKnownVocabularyTask(mDataSource, amount, offset)
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
                new GetWorstKnownVocabularyTask(mDataSource, amount, offset)
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
