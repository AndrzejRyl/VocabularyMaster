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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fleenmobile.vocabularymaster.BuyVocabularyPopupContract;
import com.fleenmobile.vocabularymaster.BuyVocabularyPopupLayout;
import com.fleenmobile.vocabularymaster.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author FleenMobile at 2016-09-26
 */

public class BuyVocabularyPopupView extends DialogFragment implements BuyVocabularyPopupContract.View {

    private boolean mActive = false;
    private Activity mActivity;
    private BuyVocabularyPopupContract.Presenter mPresenter;

    @BindView(R.id.buy_vocabulary_button)
    protected Button buyButton;
    @BindView(R.id.buy_vocabulary_error_button)
    protected Button errorButton;
    @BindView(R.id.buy_vocabulary_progress_bar)
    protected ProgressBar progressBar;
    @BindView(R.id.buy_vocabulary_popup_header)
    protected TextView headerTV;

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
        // TODO: Here I have to request price for setPrice method
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
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void setPresenter(BuyVocabularyPopupContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void setPrice(String price) {
        buyButton.setText(mActivity.getResources().getString(R.string.buy_for, price));
    }

    @Override
    public void changeView(BuyVocabularyPopupLayout type) {

        hideButtonsAndProgress();

        switch (type) {
            case NORMAL:
                headerTV.setText(mActivity.getResources().getString(R.string.buy_vocabulary_header));
                buyButton.setVisibility(View.VISIBLE);
                break;
            case SUCCESS:
                headerTV.setText(mActivity.getResources().getString(R.string.buy_vocabulary_success));
                break;
            case ERROR:
                headerTV.setText(mActivity.getResources().getString(R.string.buy_vocabulary_error));
                errorButton.setVisibility(View.VISIBLE);
                break;
            case BOUGHT:
                headerTV.setText(mActivity.getResources().getString(R.string.buy_vocabulary_success));
                break;
            case PROGRESS:
                headerTV.setText(mActivity.getResources().getString(R.string.adding_vocabulary));
                progressBar.setVisibility(View.VISIBLE);
                break;
        }

    }

    private void hideButtonsAndProgress() {
        progressBar.setVisibility(View.GONE);
        buyButton.setVisibility(View.GONE);
        errorButton.setVisibility(View.GONE);
    }

    @OnClick(R.id.buy_vocabulary_button)
    public void onBuy(View v) {
        changeView(BuyVocabularyPopupLayout.PROGRESS);
        mPresenter.buy();
    }

    @OnClick(R.id.buy_vocabulary_error_button)
    public void onTryAgain(View v) {
        changeView(BuyVocabularyPopupLayout.PROGRESS);
        mPresenter.buy();
    }
}
