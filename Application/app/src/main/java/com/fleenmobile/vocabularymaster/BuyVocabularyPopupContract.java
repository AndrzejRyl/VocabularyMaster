package com.fleenmobile.vocabularymaster;

/**
 * @author FleenMobile at 2016-09-26
 */

public interface BuyVocabularyPopupContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {


        /**
         * Parent layout is hidden
         */
        void onHide();

        /**
         * Parent layout is revealed
         */
        void onShow();
    }
}
