package com.fleenmobile.vocabularymaster.revision;

import android.support.v4.app.Fragment;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;

import java.util.List;
import java.util.Map;

/**
 *
 * This is a screen with simple statistics text view on the bottom
 * and view pager with revision layout. It consists of one text view
 * (vocabulary word) and at least one edit text (for vocabulary translation).
 * After clicking on the button edit texts become disabled and display whether
 * translation was correct or not. User can also swipe right to next vocabulary.
 *
 * @author FleenMobile at 2016-09-07
 */
public class RevisionFragment extends Fragment implements RevisionContract.View {

    private RevisionContract.Presenter mPresenter;
    private RevisionVocabularyAdapter mAdapter;

    public static RevisionFragment newInstance() {
        return new RevisionFragment();
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
    public void onVocabularyLoaded(List<Vocabulary> vocabulary) {
        // TODO
    }

    @Override
    public void onVocabularyFinished() {
        // TODO
    }

    @Override
    public void onNewVocabularyChosen(Vocabulary vocabulary) {
        // TODO
    }

    @Override
    public void setTranslationIndicators(Map<Integer, TranslationIndicator> indicators) {
        // TODO
    }

    @Override
    public boolean isActive() {
        // TODO
        return false;
    }

    @Override
    public void setPresenter(RevisionContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
