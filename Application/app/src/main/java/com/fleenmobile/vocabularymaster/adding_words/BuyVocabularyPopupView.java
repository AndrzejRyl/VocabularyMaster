package com.fleenmobile.vocabularymaster.adding_words;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.fleenmobile.vocabularymaster.BuyVocabularyPopupContract;

/**
 * @author FleenMobile at 2016-09-26
 */

public class BuyVocabularyPopupView extends RelativeLayout implements BuyVocabularyPopupContract.View {
    public BuyVocabularyPopupView(Context context) {
        super(context);
    }

    public BuyVocabularyPopupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BuyVocabularyPopupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setPresenter(BuyVocabularyPopupContract.Presenter presenter) {

    }

    @Override
    public void onHide() {

        // TODO
    }

    @Override
    public void onShow() {

        // TODO
    }
}
