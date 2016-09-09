package com.fleenmobile.vocabularymaster.learning;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.learning.domain.GetRandomVocabularyTask;
import com.fleenmobile.vocabularymaster.learning.domain.MarkVocabularyAsLearntTask;
import com.fleenmobile.vocabularymaster.statistics.domain.GetMainStatsTask;
import com.google.common.collect.Lists;

import java.util.List;

import rx.subscriptions.CompositeSubscription;

/**
 * @author FleenMobile at 2016-09-07
 */
public class LearningPresenter implements LearningContract.Presenter {
    @NonNull
    private LearningContract.View mView;
    @NonNull
    private LearningPhase mPhase;
    @NonNull
    private List<Vocabulary> mVocabularyToRevise = Lists.newArrayList();
    @NonNull
    private List<Vocabulary> mVocabularyToLearn = Lists.newArrayList();

    @NonNull
    private GetMainStatsTask mGetMainStats;
    @NonNull
    private GetRandomVocabularyTask mGetRandomVocabulary;
    @NonNull
    private MarkVocabularyAsLearntTask mMarkVocabularyAsLearnt;

    private boolean mInitialLoadCompleted = false;

    @NonNull
    CompositeSubscription mSubscriptions;


    @Override
    public void loadVocabulary() {
        // TODO
    }

    @Override
    public void loadStatistics() {
        // TODO
    }

    @Override
    public void markVocabularyAsLearnt(List<Vocabulary> vocabulary) {
        // TODO
    }

    @Override
    public void onPhaseFinished() {
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
