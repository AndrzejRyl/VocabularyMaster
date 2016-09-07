package com.fleenmobile.vocabularymaster.revision;

import com.fleenmobile.vocabularymaster.BasePresenter;
import com.fleenmobile.vocabularymaster.BaseView;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;

import java.util.List;
import java.util.Map;

/**
 *
 * This is one the two most important screens in this app.
 * Here user is presented with all words that he has learnt.
 * He can fill out EditTexts with all translations of vocabulary
 * on the screen that he knowns. If all are corect he is presented
 * with success message and if not he is presented with error
 * message and incorrect translations are highlighted.
 * After finishing with one vocabulary he can proceed to next one
 * by swiping right.
 *
 * @author FleenMobile at 2016-09-07
 */
public interface RevisionContract {

    interface Presenter extends BasePresenter {
        /**
         * Loads a set of vocabulary to revise. They are loaded in bulk of 10.
         */
        void loadVocabulary();

        /**
         * Updates vocabulary's parameters (like total correct tries) in db
         *
         * @param vocabulary Vocabulary to be updated
         */
        void updateVocabulary(Vocabulary vocabulary);

        /**
         * Sets mCurrentVocabulary variable. It will be needed for verification
         *
         * @param vocabulary Vocabulary that user has swiped to
         */
        void onNewVocabularyChosen(Vocabulary vocabulary);

        /**
         * Checks translations provided by user with correct translations
         * of current vocabulary
         *
         * @param translations List of strings from edit texts
         */
        void checkTranslations(List<String> translations);
    }

    interface View extends BaseView<Presenter> {
        /**
         * Loads vocabulary into adapter
         *
         * @param vocabulary List of vocabulary loaded from DB
         */
        void onVocabularyLoaded(List<Vocabulary> vocabulary);

        /**
         * Restarts the procedure as we want to start revising from the top
         */
        void onVocabularyFinished();

        /**
         * Called by adapter after user swipe
         *
         * @param vocabulary Vocabulary that user has swiped to
         */
        void onNewVocabularyChosen(Vocabulary vocabulary);

        /**
         * Sets indicators on edit texts
         *
         * @param indicators Map of edit texts indices mapped to CORRECT or INCORRECT indicator
         */
        void setTranslationIndicators(Map<Integer, TranslationIndicator> indicators);
    }
}
