package com.fleenmobile.vocabularymaster.statistics;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;
import com.fleenmobile.vocabularymaster.statistics.domain.GetLearntVocabularyTask;
import com.fleenmobile.vocabularymaster.statistics.domain.GetMainStatsTask;
import com.fleenmobile.vocabularymaster.statistics.domain.GetTopKnownVocabularyTask;
import com.fleenmobile.vocabularymaster.statistics.domain.GetWorstKnownVocabularyTask;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * @author FleenMobile at 2016-09-07
 */
public class StatisticsPresenter implements StatisticsContract.Presenter {

    @NonNull
    private StatisticsContract.View mView;

    @NonNull
    private GetMainStatsTask mGetMainStats;
    @NonNull
    private GetLearntVocabularyTask mGetLearntVocabulary;
    @NonNull
    private GetTopKnownVocabularyTask mGetTopKnownVocabulary;
    @NonNull
    private GetWorstKnownVocabularyTask mGetWorstKnownVocabulary;
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
        // TODO
    }

    @Override
    public void loadLearntVocabulary(int amount, int offset) {
        // TODO
    }

    @Override
    public void loadTopKnownVocabulary(int amount, int offset) {
        // TODO
    }

    @Override
    public void loadWorstKnownVocabulary(int amount, int offset) {
        // TODO
    }

    @Override
    public void loadFileChooser() {
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
