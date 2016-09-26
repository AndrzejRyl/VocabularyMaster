package com.fleenmobile.vocabularymaster.adding_words;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * Popup first showing the user button taking him to file manager in order to
 * choose the file and than - through progress bar - it changes to success
 * message or error one
 *
 * @author FleenMobile at 2016-09-07
 */
public class AddFilePopupView extends RelativeLayout implements AddFilePopupContract.View  {

    private AddFilePopupContract.Presenter mPresenter;

    public static AddFilePopupView newInstance(Context context) {
        return new AddFilePopupView(context);
    }

    public AddFilePopupView(Context context) {
        super(context);
    }

    public AddFilePopupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AddFilePopupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void subscribe() {
        mPresenter.subscribe();
    }

    public void unsubscribe() {
        mPresenter.unsubscribe();
    }

    @Override
    public void onSuccess(int amount) {
        //TODO
    }

    @Override
    public void onProgress() {
        //TODO
    }

    @Override
    public void onError() {
        //TODO
    }

    @Override
    public boolean isActive() {
        //TODO
                return false;
    }

    @Override
    public void setPresenter(AddFilePopupContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
