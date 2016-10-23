package com.fleenmobile.vocabularymaster.adding_words;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fleenmobile.vocabularymaster.R;

import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * Popup first showing the user button taking him to file manager in order to
 * choose the file and than - through progress bar - it changes to success
 * message or error one
 *
 * @author FleenMobile at 2016-09-07
 */
public class AddFilePopupView extends DialogFragment implements AddFilePopupContract.View  {

    private AddFilePopupContract.Presenter mPresenter;
    private boolean mActive = false;
    private Activity mActivity;

    public static AddFilePopupView newInstance() {
        return new AddFilePopupView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.v_add_file, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (mActive) return;
        super.show(manager, tag);
        mActive = true;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        mActive = false;
        super.onDismiss(dialog);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
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
    public void onHide() {
        // TODO
    }

    @Override
    public void onShow() {
        // TODO
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void setPresenter(AddFilePopupContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
