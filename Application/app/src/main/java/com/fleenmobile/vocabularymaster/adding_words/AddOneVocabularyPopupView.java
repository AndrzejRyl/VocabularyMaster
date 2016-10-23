package com.fleenmobile.vocabularymaster.adding_words;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
public class AddOneVocabularyPopupView extends DialogFragment implements AddOneVocabularyPopupContract.View {

    public static final String TAG = AddOneVocabularyPopupView.class.getName();
    @BindView(R.id.add_one_vocabulary_popup_header)
    protected TextView headerTV;
    @BindView(R.id.add_one_vocabulary_word)
    protected EditText wordET;
    @BindView(R.id.add_one_vocabulary_translation)
    protected EditText translationET;
    @BindView(R.id.add_one_vocabulary_word_layout)
    protected TextInputLayout wordETLayout;
    @BindView(R.id.add_one_vocabulary_translation_layout)
    protected TextInputLayout translationETLayout;
    @BindView(R.id.add_one_vocabulary_button)
    protected Button doneButton;
    @BindView(R.id.add_one_vocabulary_success_button)
    protected Button successButton;
    @BindView(R.id.add_one_vocabulary_error_button)
    protected Button errorButton;

    private static AddOneVocabularyPopupContract.Presenter mPresenter;
    private boolean mActive = false;
    private Activity mActivity;

    public static AddOneVocabularyPopupView newInstance() {
        return new AddOneVocabularyPopupView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.v_add_one_vocabulary, container, false);
        ButterKnife.bind(this, rootView);
        wordET.requestFocus();
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
        changeView(AddOneVocabularyPopupType.NORMAL);
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
    public void setWordETError() {
        if (mActivity == null) {
            onError();
            return;
        }
        wordET.requestFocus();
        wordETLayout.setErrorEnabled(true);
        wordETLayout.setError(mActivity.getResources().getString(R.string.one_word_error));
    }

    @Override
    public void setTranslationETError() {
        if (mActivity == null) {
            onError();
            return;
        }
        translationET.requestFocus();
        translationETLayout.setErrorEnabled(true);
        translationETLayout.setError(mActivity.getResources().getString(R.string.translation_error));
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
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void setPresenter(AddOneVocabularyPopupContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @OnClick(R.id.add_one_vocabulary_button)
    public void onDone(View v) {
        if (mActivity != null)
            ActivityUtils.hideKeyboard((Activity) mActivity);

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
        if (mActivity != null)
            ActivityUtils.hideKeyboard((Activity) mActivity);
        else
            return;

        switch (type) {
            case NORMAL:
                wordET.setText("");
                translationET.setText("");
                wordETLayout.setErrorEnabled(false);
                translationETLayout.setErrorEnabled(false);
                headerTV.setText(mActivity.getResources().getString(R.string.add_one_new_word));
                wordET.setVisibility(View.VISIBLE);
                translationET.setVisibility(View.VISIBLE);
                doneButton.setText(mActivity.getResources().getString(R.string.done));
                doneButton.setVisibility(View.VISIBLE);
                successButton.setVisibility(View.GONE);
                errorButton.setVisibility(View.GONE);
                break;
            case SUCCESS:
                headerTV.setText(mActivity.getResources().getString(R.string.add_one_vocabulary_success, wordET.getText().toString()));
                wordET.setVisibility(View.GONE);
                translationET.setVisibility(View.GONE);
                successButton.setVisibility(View.VISIBLE);
                doneButton.setVisibility(View.GONE);
                errorButton.setVisibility(View.GONE);
                break;
            case ERROR:
                headerTV.setText(mActivity.getResources().getString(R.string.something_went_wrong));
                wordET.setVisibility(View.GONE);
                translationET.setVisibility(View.GONE);
                errorButton.setVisibility(View.VISIBLE);
                successButton.setVisibility(View.GONE);
                doneButton.setVisibility(View.GONE);
                break;
        }
    }
}
