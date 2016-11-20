package com.fleenmobile.vocabularymaster;

/**
 * @author FleenMobile at 2016-09-26
 */

public interface BuyVocabularyPopupContract {

    interface Presenter extends BasePresenter {
        void buy();
    }

    interface View extends BaseView<Presenter> {
        void setPrice(String price);

        void changeView(BuyVocabularyPopupLayout type);
    }
}
