package com.fleenmobile.vocabularymaster.adding_words;

import com.fleenmobile.vocabularymaster.BasePresenter;
import com.fleenmobile.vocabularymaster.BaseView;

/**
 *
 * This popup lets user add one vocabulary to the database.
 *
 * @author FleenMobile at 2016-09-07
 */
public interface AddOneWordPopupContract {

    interface Presenter extends BasePresenter{

        /**
         * Checks whether those words are not empty and doesn't contain
         * illegal characters
         *
         * @param word
         * @param translation
         */
        void validateFields(String word, String translation);

        /**
         * Constructs Vocabulary based on those two strings and
         * saves it to the DB
         *
         * @param word
         * @param translation
         */
        void addVocabulary(String word, String translation);
    }

    interface View extends BaseView<Presenter>{
        /**
         * Sets error message in EditText containing word
         */
        void setWordETError();

        /**
         * Sets error message in EditText containing translation
         */
        void setTranslationETError();

        /**
         * Shows success message to the user
         */
        void onSuccess();

        /**
         * Shows error message to the user and lets him add word again
         */
        void onError();
    }
}
