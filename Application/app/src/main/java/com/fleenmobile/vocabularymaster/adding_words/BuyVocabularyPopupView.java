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

import com.fleenmobile.vocabularymaster.BuyVocabularyPopupContract;
import com.fleenmobile.vocabularymaster.R;

import butterknife.ButterKnife;

/**
 * @author FleenMobile at 2016-09-26
 */

public class BuyVocabularyPopupView extends DialogFragment implements BuyVocabularyPopupContract.View {

    private boolean mActive = false;
    private Activity mActivity;

    public static BuyVocabularyPopupView newInstance() {
        return new BuyVocabularyPopupView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.v_buy_vocabulary, container, false);
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
