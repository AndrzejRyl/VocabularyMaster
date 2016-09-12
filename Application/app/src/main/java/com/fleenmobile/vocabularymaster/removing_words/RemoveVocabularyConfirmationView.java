package com.fleenmobile.vocabularymaster.removing_words;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;

import java.util.List;

/**
 *
 * This is a simple popup with a list of vocabulary (chosen by user to be removed)
 * and two buttons: OK and Cancel
 *
 * @author FleenMobile at 2016-09-09
 */
public class RemoveVocabularyConfirmationView extends RelativeLayout implements RemoveVocabularyConfirmationPopupContract.View {

    private RemoveVocabularyConfirmationPopupContract.Presenter mPresenter;
    private List<Vocabulary> mVocabularyToRemove;
    private ArrayAdapter<Vocabulary> mAdapter;

    public RemoveVocabularyConfirmationView(Context context) {
        super(context);
    }

    public static RemoveVocabularyConfirmationView newInstance(Context context) {
        return new RemoveVocabularyConfirmationView(context);
    }

    public void subscribe() {
        mPresenter.subscribe();
    }

    public void unsubscribe() {
        mPresenter.unsubscribe();
    }

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
        mPresenter = presenter;
    }
}
