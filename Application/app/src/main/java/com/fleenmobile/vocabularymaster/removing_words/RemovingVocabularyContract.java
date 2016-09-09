package com.fleenmobile.vocabularymaster.removing_words;

import android.widget.EditText;

import com.fleenmobile.vocabularymaster.BasePresenter;
import com.fleenmobile.vocabularymaster.BaseView;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;

import java.util.List;

/**
 *
 * This screen allows user to remove vocabulary from the db.
 * At first he's presented with a list of all available vocabulary.
 * He can than filter it by search pattern and choose from the list by
 * clicking on list items. After that he can click remove button
 * at he will be shown a confirmation popup with a list of chosen
 * vocabulary. After confirming all chosen vocabulary will be removed
 * from db.
 *
 * @author FleenMobile at 2016-09-09
 */
public interface RemovingVocabularyContract {

    interface Presenter extends BasePresenter {
        /**
         * Loads all vocabulary available in the database
         */
        void loadVocabulary();

        /**
         * Removes all vocabulary chosen by the user from the database
         */
        void removeVocabulary();

        /**
         * Adds/removes vocabulary chosen by the user to the collection of vocabulary
         * to be removed
         *
         * @param vocabulary Vocabulary connected to list item clicked by the user
         */
        void onVocabularyChosen(Vocabulary vocabulary);
    }

    interface View extends BaseView<Presenter> {
        /**
         * Loads vocabulary loaded from DB into an adapter
         *
         * @param vocabulary List of vocabulary items from DB
         */
        void onVocabularyLoaded(List<Vocabulary> vocabulary);

        /**
         * Updates adapter with new list of vocabulary (filtered by search pattern)
         *
         * @param vocabulary List of vocabulary matching pattern written by user
         */
        void onVocabularyFiltered(List<Vocabulary> vocabulary);

        /**
         * Starts popup that asks for confirmation about removing chosen words
         *
         * @param vocabulary List of vocabulary chosen by user
         */
        void showConfirmationPopup(List<Vocabulary> vocabulary);

        /**
         * Returns search bar. Presenter will attach filter task to it
         *
         * @return EditText representing search bar
         */
        EditText getSearchBar();
    }
}
