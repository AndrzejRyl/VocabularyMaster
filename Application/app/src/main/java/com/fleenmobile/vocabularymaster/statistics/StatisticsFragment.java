package com.fleenmobile.vocabularymaster.statistics;

import android.support.v4.app.Fragment;

import com.fleenmobile.vocabularymaster.adding_words.AddFilePopupContract;
import com.fleenmobile.vocabularymaster.data.model.Stats;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * This fragment contains three main parts
 *  - statistics about all learnt vocabulary (a list with translations)
 *  - statistics about top known vocabulary (a list with percentage of correct tries sorted by it desc)
 *  - statistics about worst known vocabulary (a list with percentage of correct tries sorted by it)
 *
 * @author FleenMobile at 2016-09-07
 */
public class StatisticsFragment extends Fragment implements StatisticsContract.View {

    private StatisticsContract.Presenter mPresenter;
    private boolean mAddOneVocabularyPopupShown = false;
    private boolean mAddFileVocabularyPopupShown = false;
    private boolean mBuyVocabularyPopupShown = false;
    private boolean mFABExpanded = false;
    private AddFilePopupContract.Presenter mAddFilePopupPresenter;

    public static StatisticsFragment newInstance() {
        return new StatisticsFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onLoadingMainStats() {
        // TODO
    }

    @Override
    public void onLoadingLearntVocabulary() {
        // TODO
    }

    @Override
    public void onLoadingTopKnownVocabulary() {
        // TODO
    }

    @Override
    public void onLoadingWorstKnownVocabulary() {
        // TODO
    }

    @Override
    public void onLoadedMainStats(Stats stats) {
        // TODO
    }

    @Override
    public void onLoadedLearntVocabulary(List<Vocabulary> vocabulary) {
        // TODO
    }

    @Override
    public void onLoadedTopKnownVocabulary(List<Vocabulary> vocabulary) {
        // TODO
    }

    @Override
    public void onLoadedWorstKnownVocabulary(List<Vocabulary> vocabulary) {
        // TODO
    }

    @Override
    public void expandFAB() {
        // TODO
    }

    @Override
    public void collapseFAB() {
        // TODO
    }

    @Override
    public void showAddOneVocabularyPopup() {
        // TODO
    }

    @Override
    public void showAddFilePopup() {
        // TODO
    }

    @Override
    public void showBuyVocabularyPopup() {
        // TODO
    }

    @Override
    public void onFileChosen(String filePath) {
        // TODO
    }

    @Override
    public boolean isActive() {
        // TODO
        return false;
    }

    @Override
    public void setPresenter(StatisticsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
