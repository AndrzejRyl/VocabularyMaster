package com.fleenmobile.vocabularymaster.adding_words;

import android.content.Context;
import android.widget.RelativeLayout;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * Fragment containing two EditTexts (one for word and one for it's translation) and a button
 * which allows user to add this vocabulary to the database.
 *
 * @author FleenMobile at 2016-09-07
 */
public class AddOneVocabularyPopupView extends RelativeLayout implements AddOneVocabularyPopupContract.View {

    private AddOneVocabularyPopupContract.Presenter mPresenter;

    public static AddOneVocabularyPopupView newInstance(Context context) {
        return new AddOneVocabularyPopupView(context);
    }

    public AddOneVocabularyPopupView(Context context) {
        super(context);
    }

    public void subscribe() {
        mPresenter.subscribe();
    }

    public void unsubscribe() {
        mPresenter.unsubscribe();
    }

    @Override
    public void setWordETError() {

        // TODO
    }

    @Override
    public void setTranslationETError() {

        // TODO
    }

    @Override
    public void onSuccess() {

        // TODO
    }

    @Override
    public void onError() {

        // TODO
    }

    @Override
    public boolean isActive() {
        // TODO
        return false;
    }

    @Override
    public void setPresenter(AddOneVocabularyPopupContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
