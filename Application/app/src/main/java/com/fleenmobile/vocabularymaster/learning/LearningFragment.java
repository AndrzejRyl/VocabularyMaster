package com.fleenmobile.vocabularymaster.learning;

import android.support.v4.app.Fragment;

import com.fleenmobile.vocabularymaster.data.model.Stats;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;

import java.util.List;

/**
 * @author FleenMobile at 2016-09-07
 */
public class LearningFragment extends Fragment implements LearningContract.View {

    private LearningContract.Presenter mPresenter;

    private VocabularyAdapter mVocabularyToLearnAdapter;
    private VocabularyAdapter mVocabularyToReviseAdapter;

    public static LearningFragment newInstance() {
        return new LearningFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void modifyLearntWordsCounter(int diff) {
        // TODO
    }

    @Override
    public void modifyWordsToReviseCounter(int diff) {
        // TODO
    }

    @Override
    public void changePhase(LearningPhase phase, List<Vocabulary> vocabulary) {
        // TODO
    }

    @Override
    public void onStatisticsLoaded(Stats stats) {
        // TODO
    }

    @Override
    public void onPhaseFinshed() {
        // TODO
    }

    @Override
    public boolean isActive() {
        // TODO
        return false;
    }

    @Override
    public void setPresenter(LearningContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
