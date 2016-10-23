package com.fleenmobile.vocabularymaster.adding_words;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fleenmobile.vocabularymaster.R;
import com.fleenmobile.vocabularymaster.utils.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Fragment containing two EditTexts (one for word and one for it's translation) and a button
 * which allows user to add this vocabulary to the database.
 *
 * @author FleenMobile at 2016-09-07
 */
public class AddOneVocabularyPopupView extends LinearLayout implements AddOneVocabularyPopupContract.View {

    @BindView(R.id.add_one_vocabulary_popup_header)
    protected TextView headerTV;
    @BindView(R.id.add_one_vocabulary_word)
    protected TextInputEditText wordET;
    @BindView(R.id.add_one_vocabulary_translation)
    protected TextInputEditText translationET;
    @BindView(R.id.add_one_vocabulary_button)
    protected Button doneButton;
    @BindView(R.id.add_one_vocabulary_success_button)
    protected Button successButton;
    @BindView(R.id.add_one_vocabulary_error_button)
    protected Button errorButton;

    private static AddOneVocabularyPopupContract.Presenter mPresenter;
    private boolean mActive = false;

    public static AddOneVocabularyPopupView newInstance(Context context) {
        return new AddOneVocabularyPopupView(context);
    }

    public AddOneVocabularyPopupView(Context context) {
        super(context);
        init();
    }

    public AddOneVocabularyPopupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AddOneVocabularyPopupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View v = inflate(getContext(), R.layout.v_add_one_vocabulary, this);
        ButterKnife.bind(this, v);
    }

    public void subscribe() {
        mPresenter.subscribe();
    }

    public void unsubscribe() {
        mPresenter.unsubscribe();
    }

    @Override
    public void setWordETError() {
        if (getContext() == null) {
            onError();
            return;
        }
        wordET.requestFocus();
        wordET.setError(getContext().getResources().getString(R.string.one_word_error));
    }

    @Override
    public void setTranslationETError() {
        if (getContext() == null) {
            onError();
            return;
        }
        translationET.requestFocus();
        translationET.setError(getContext().getResources().getString(R.string.translation_error));
        requestLayout();
    }

    @Override
    public void onSuccess() {
        changeView(AddOneVocabularyPopupType.SUCCESS);
    }

    @Override
    public void onError() {
        changeView(AddOneVocabularyPopupType.ERROR);
    }

    @Override
    public void onHide() {
        ActivityUtils.hideKeyboard((Activity) getContext());
        wordET.setText("");
        translationET.setText("");
        mActive = false;
    }

    @Override
    public void onShow() {
        wordET.requestFocus();
        mActive = true;
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void setPresenter(AddOneVocabularyPopupContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @OnClick(R.id.add_one_vocabulary_button)
    public void onDone(View v) {
        if (getContext() != null)
            ActivityUtils.hideKeyboard((Activity) getContext());

        String word = wordET.getText().toString();
        String translation = translationET.getText().toString();
        mPresenter.addVocabulary(word, translation);
    }

    @OnClick(R.id.add_one_vocabulary_success_button)
    public void onAnotherOne(View v) {
        changeView(AddOneVocabularyPopupType.NORMAL);
    }

    @OnClick(R.id.add_one_vocabulary_error_button)
    public void onError(View v) {
        changeView(AddOneVocabularyPopupType.NORMAL);
    }

    private void changeView(AddOneVocabularyPopupType type) {
        if (getContext() != null)
            ActivityUtils.hideKeyboard((Activity) getContext());
        else
            return;

        switch (type) {
            case NORMAL:
                headerTV.setText(getContext().getResources().getString(R.string.add_one_new_word));
                wordET.setVisibility(View.VISIBLE);
                translationET.setVisibility(View.VISIBLE);
                doneButton.setText(getContext().getResources().getString(R.string.done));
                doneButton.setVisibility(View.VISIBLE);
                successButton.setVisibility(View.GONE);
                errorButton.setVisibility(View.GONE);
                break;
            case SUCCESS:
                headerTV.setText(getContext().getResources().getString(R.string.add_one_vocabulary_success, wordET.getText().toString()));
                wordET.setVisibility(View.GONE);
                translationET.setVisibility(View.GONE);
                successButton.setVisibility(View.VISIBLE);
                doneButton.setVisibility(View.GONE);
                errorButton.setVisibility(View.GONE);
                break;
            case ERROR:
                headerTV.setText(getContext().getResources().getString(R.string.something_went_wrong));
                wordET.setVisibility(View.GONE);
                translationET.setVisibility(View.GONE);
                errorButton.setVisibility(View.VISIBLE);
                successButton.setVisibility(View.GONE);
                doneButton.setVisibility(View.GONE);
                break;
        }
    }
}
