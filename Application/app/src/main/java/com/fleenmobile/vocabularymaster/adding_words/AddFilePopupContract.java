package com.fleenmobile.vocabularymaster.adding_words;

import android.content.Context;

import com.fleenmobile.vocabularymaster.BasePresenter;
import com.fleenmobile.vocabularymaster.BaseView;

/**
 *
 * This popup lets user add vocabulary from the file on the device
 *
 * @author FleenMobile at 2016-09-07
 */
public interface AddFilePopupContract {

    interface Presenter extends BasePresenter {
        /**
         * Shows system popup letting user to choose his file manager or (if there is only one)
         * takes him to that file manager and lets him choose file with vocabulary
         */
        void showFileChooser();

        /**
         * Adds vocabulary from file to the database
         *
         * @param filePath Path of the file with vocabulary
         */
        void addVocabulary(String filePath, Context context);
    }

    interface View extends BaseView<Presenter> {
        /**
         * Show the user message about success
         *
         * @param amount The amount of words that have been added to the database
         */
        void onSuccess(int amount);

        /**
         * Shows the user progress bar while saving words to the database is taking place
         */
        void onProgress();

        /**
         * Show the user error message
         */
        void onError();

        /**
         * Show system file chooser allowing user to choose
         * file with vocabulary
         */
        void showFileChooser();
    }
}
