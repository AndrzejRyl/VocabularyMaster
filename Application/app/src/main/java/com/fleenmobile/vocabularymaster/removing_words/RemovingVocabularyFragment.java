package com.fleenmobile.vocabularymaster.removing_words;

import android.support.v4.app.Fragment;
import android.widget.EditText;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;

import java.util.List;

/**
 *
 * Screen showing listview with vocabulary from DB. It can be filtered by
 * patern in search bar above that list. After choosing at least one item
 * FAB with remove icon pops up. It takes user to popup with confirmation
 * where he can either remove chosen words or cancel.
 *
 * @author FleenMobile at 2016-09-09
 */
public class RemovingVocabularyFragment extends Fragment implements RemovingVocabularyContract.View {

    private RemovingVocabularyContract.Presenter mPresenter;
    private RemovingVocabularyAdapter mAdapter;

    @Override
    public void onVocabularyLoaded(List<Vocabulary> vocabulary) {
        // TODO
    }

    @Override
    public void onVocabularyFiltered(List<Vocabulary> vocabulary) {
        // TODO
    }

    @Override
    public void showConfirmationPopup(List<Vocabulary> vocabulary) {
        // TODO
    }

    @Override
    public EditText getSearchBar() {
        // TODO
        return null;
    }

    @Override
    public boolean isActive() {
        // TODO
        return false;
    }

    @Override
    public void setPresenter(RemovingVocabularyContract.Presenter presenter) {
        // TODO
    }
}
