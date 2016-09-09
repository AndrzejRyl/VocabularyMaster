package com.fleenmobile.vocabularymaster.removing_words;

import android.widget.ArrayAdapter;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;

import java.util.List;

/**
 *
 * This is a simple popup with a list of vocabulary (chosen by user to be removed)
 * and two buttons: OK and Cancel
 *
 * @author FleenMobile at 2016-09-09
 */
public class RemoveVocabularyConfirmationView implements RemoveVocabularyConfirmationPopupContract.View {

    private RemoveVocabularyConfirmationPopupContract.Presenter mPresenter;
    private List<Vocabulary> mVocabularyToRemove;
    private ArrayAdapter<Vocabulary> mAdapter;

    @Override
    public void loadVocabulary(List<Vocabulary> vocabulary) {
        // TODO
    }

    @Override
    public boolean isActive() {
        // TODO
        return false;
    }

    @Override
    public void setPresenter(RemoveVocabularyConfirmationPopupContract.Presenter presenter) {
        // TODO
    }
}
