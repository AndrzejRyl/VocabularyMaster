package com.fleenmobile.vocabularymaster.removing_words;

import com.fleenmobile.vocabularymaster.BasePresenter;
import com.fleenmobile.vocabularymaster.BaseView;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;

import java.util.List;

/**
 *
 * This popup shows user a list of vocabulary that he has chosen.
 * He can than click OK removing all from DB or click cancel
 * and get back to RemovingVocabularyFragment.
 *
 * @author FleenMobile at 2016-09-09
 */
public interface RemoveVocabularyConfirmationPopupContract {

    interface Presenter extends BasePresenter {
        /**
         * Starts removing task in parent presenter
         */
        void onConfirmed();

        /**
         * Hides the popup
         */
        void onCancelled();
    }

    interface View extends BaseView<Presenter> {
        /**
         * Loads vocabulary chosen by the user into list adapter
         *
         * @param vocabulary Vocabulary chosen by the user
         */
        void loadVocabulary(List<Vocabulary> vocabulary);
    }
}
