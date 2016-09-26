package com.fleenmobile.vocabularymaster.adding_words;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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

    @BindView(R.id.add_one_vocabulary_word)
    protected TextInputEditText wordET;
    @BindView(R.id.add_one_vocabulary_translation)
    protected TextInputEditText translationET;
    @BindView(R.id.add_one_vocabulary_button)
    protected Button doneButton;

    private AddOneVocabularyPopupContract.Presenter mPresenter;

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
    public void onHide() {
        ActivityUtils.hideKeyboard((Activity) getContext());
        wordET.setText("");
        translationET.setText("");
    }

    @Override
    public void onShow() {
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

    @OnClick(R.id.add_one_vocabulary_button)
    public void onDone(View v) {
        if (getContext() != null)
            ActivityUtils.hideKeyboard((Activity) getContext());

        //TODO Handle button
    }
}
