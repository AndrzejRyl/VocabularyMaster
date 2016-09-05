package com.fleenmobile.vocabularymaster;

/**
 * @author FleenMobile at 2016-09-06
 */
public interface BaseView<T> {

    /**
     * Method used by presenter to check whether it can communicate changes to UI
     * @return
     */
    boolean isActive();

    void setPresenter(T presenter);
}
