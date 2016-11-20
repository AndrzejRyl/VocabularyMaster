package com.fleenmobile.vocabularymaster.statistics;

import com.fleenmobile.vocabularymaster.BasePresenter;
import com.fleenmobile.vocabularymaster.BaseView;
import com.fleenmobile.vocabularymaster.data.model.Stats;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;

import java.util.List;

/**
 * This screen shows user all statistics about how he's learning.
 * All vocabulary that he's learnt; best known vocabulary and worst known
 *
 * @author FleenMobile at 2016-09-07
 */
public interface StatisticsContract {

    interface Presenter extends BasePresenter {
        /**
         * Loads main stats like count of all learnt vocabulary or count
         * of all vocabulary
         */
        void loadMainStats();

        /**
         * Loads vocabulary only from the ones that has been learnt sorted by
         * percentage of correct tries desc
         *
         * @param amount Amount of vocabulary to be loaded
         * @param offset Offset strictly for paging
         */
        void loadTopKnownVocabulary(int amount, int offset);

        /**
         * Loads vocabulary only from the ones that has been learnt sorted by
         * percentage of correct tries
         *
         * @param amount Amount of vocabulary to be loaded
         * @param offset Offset strictly for paging
         */
        void loadWorstKnownVocabulary(int amount, int offset);

        /**
         *
         * @param v
         */
        void onFabMenu(android.view.View v);
    }

    interface View extends BaseView<Presenter> {
        /**
         * Shows a progress bar in section with main stats
         */
        void onLoadingMainStats();

        /**
         * Shows a progress bar in section with top known vocabulary
         */
        void onLoadingTopKnownVocabulary();

        /**
         * Shows a progress bar in section with worst known vocabulary
         */
        void onLoadingWorstKnownVocabulary();

        /**
         * Shows main stats to the user
         *
         * @param stats A map with stats
         */
        void onLoadedMainStats(Stats stats);

        /**
         * Shows a list of top known vocabulary
         *
         * @param vocabulary A list of vocabulary that is loaded now
         */
        void onLoadedTopKnownVocabulary(List<Vocabulary> vocabulary);

        /**
         * Shows a list of worst known vocabulary
         *
         * @param vocabulary A list of vocabulary that is loaded now
         */
        void onLoadedWorstKnownVocabulary(List<Vocabulary> vocabulary);

        /**
         * Expands floating action button that has three options
         * - add one vocabulary
         * - add file with vocabulary
         * - buy vocabulary
         */
        void expandFAB();

        /**
         * Collapses floating action button with additional options
         */
        void collapseFAB();

        /**
         * Returns this path to the AddFilePopupPresenter
         *
         * @param filePath Path of a file with vocabulary (chosen by user on AddFilePopup)
         */
        void onFileChosen(String filePath);
    }
}
