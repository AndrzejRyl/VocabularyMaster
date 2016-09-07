package com.fleenmobile.vocabularymaster.learning;

import com.fleenmobile.vocabularymaster.BasePresenter;
import com.fleenmobile.vocabularymaster.BaseView;
import com.fleenmobile.vocabularymaster.data.model.Stats;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;

import java.util.List;

/**
 *
 * This screen is one of two main screens in the app.
 * Here user can learn new vocabulary. He's presented with some textviews
 * summarizing his achievements and view pager on which new words are displayed.
 *
 * User can be in two phases
 *  1. Memorizing - user is presented with totally new words. After memorizing one
 *  he can than swipe right to see next one. He can also swipe left in order to recollect last one.
 *  2. Revising - user is presented with words that he just learnt but translation is hidden behing
 *  HINT text. He has to remember what the translation was. If he cannot, he can click on HINT
 *  text and the translation will be revealed. Again - after finishing with the word - he can proceed
 *  to next one by swiping right
 *
 * @author FleenMobile at 2016-09-07
 */
public interface LearningContract {

    interface Presenter extends BasePresenter {
        /**
         * Loads new set of vocabulary to learn. Vocabulary
         * will be downloaded in the bulk of 10.
         */
        void loadVocabulary();

        /**
         * Loads initial set of statistics for textviews under and above view pager
         * with vocabulary
         */
        void loadStatistics();

        /**
         * Sets learnt flag on all vocabulary learnt by the user (vocabulary is learnt if
         * the user finished revising phase)
         * @param vocabulary Vocabulary that went through memorizing and revising phase
         */
        void markVocabularyAsLearnt(List<Vocabulary> vocabulary);

        /**
         * Changes the phase of learning. That triggers one of two tasks:
         *  1. Loading new words to learn
         *  2. Marking all words that are currently being in usage as learnt
         */
        void onPhaseFinished();

    }

    interface View extends BaseView<Presenter> {
        /**
         * Modifies counter of learnt words (displayed under view pager)
         *
         * @param diff +1 if user swiped right, -1 if user swiped left
         */
        void modifyLearntWordsCounter(int diff);

        /**
         * Modifies counter of words to revise (displayed under view pager)
         *
         * @param diff +1 if user swiped right, -1 if user swiped left
         */
        void modifyWordsToReviseCounter(int diff);

        /**
         * Changes phase of learning which means changing phase indicator,
         * statistics type and way of displaying translation (in revising phase
         * translation is hidden)
         *
         * @param phase One of two: MEMORIZNG or REVISING
         * @param vocabulary Vocabulary to be displayed on view pager this phase
         */
        void changePhase(LearningPhase phase, List<Vocabulary> vocabulary);

        /**
         * Sets stats variable which is used for textview above view pager
         *
         * @param stats Map of statistics like count of learnt words
         */
        void onStatisticsLoaded(Stats stats);

        /**
         * Called by adapter after vocabulary in this phase is finished
         */
        void onPhaseFinshed();
    }
}
