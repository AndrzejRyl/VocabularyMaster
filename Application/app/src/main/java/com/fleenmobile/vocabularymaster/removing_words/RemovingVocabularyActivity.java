package com.fleenmobile.vocabularymaster.removing_words;

import android.app.Activity;
import android.os.Bundle;

import com.fleenmobile.vocabularymaster.R;
import com.fleenmobile.vocabularymaster.application.VocabularyApplication;
import com.fleenmobile.vocabularymaster.data.di.DataComponent;
import com.fleenmobile.vocabularymaster.removing_words.di.DaggerRemovingVocabularyComponent;
import com.fleenmobile.vocabularymaster.removing_words.di.RemovingVocabularyModule;
import com.fleenmobile.vocabularymaster.utils.GoogleAnalyticsHelper;
import com.fleenmobile.vocabularymaster.utils.LogWrapper;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class RemovingVocabularyActivity extends Activity {

    private static final String TAG = RemovingVocabularyActivity.class.getName();

    @Inject
    RemovingVocabularyPresenter mPresenter;
    @Inject
    RemoveVocabularyConfirmationPresenter mConfirmationPresenter;
    private RemovingVocabularyFragment mRemovingVocabularyFragment;
    private RemoveVocabularyConfirmationView mRemoveVocabularyConfirmationPopup;

    @Inject
    LogWrapper mLogWrapper;
    @Inject
    GoogleAnalyticsHelper mAnalyticsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_removing_vocabulary);

        // TODO : Check for existing fragments
        mRemovingVocabularyFragment = RemovingVocabularyFragment.newInstance();
        mRemoveVocabularyConfirmationPopup = RemoveVocabularyConfirmationView.newInstance(this);

        // Create presenters
        DataComponent dataComponent = ((VocabularyApplication)getApplication()).getDataComponent();
        DaggerRemovingVocabularyComponent.builder()
                .removingVocabularyModule(new RemovingVocabularyModule(mRemovingVocabularyFragment, mRemoveVocabularyConfirmationPopup))
                .dataComponent(dataComponent)
                .build()
                .inject(this);

        checkNotNull(mPresenter);
        checkNotNull(mConfirmationPresenter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mAnalyticsHelper.sendScreenEvent(TAG);
        mLogWrapper.logDebug(TAG, "onResume");

        // Call subscribe on presenters of views that do not
        // have onResume method (extendning RelativeLayout i.e.)
        mRemoveVocabularyConfirmationPopup.subscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Call ussubscribe on presenters of views that do not
        // have onDestroy method (extendning RelativeLayout i.e.)
        if (mRemoveVocabularyConfirmationPopup != null) mRemoveVocabularyConfirmationPopup.unsubscribe();
    }
}
