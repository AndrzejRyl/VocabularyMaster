package com.fleenmobile.vocabularymaster.revision;

import android.app.Activity;
import android.os.Bundle;

import com.fleenmobile.vocabularymaster.R;
import com.fleenmobile.vocabularymaster.application.VocabularyApplication;
import com.fleenmobile.vocabularymaster.data.di.DataComponent;
import com.fleenmobile.vocabularymaster.revision.di.DaggerRevisionComponent;
import com.fleenmobile.vocabularymaster.revision.di.RevisionModule;
import com.fleenmobile.vocabularymaster.utils.GoogleAnalyticsHelper;
import com.fleenmobile.vocabularymaster.utils.LogWrapper;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class RevisionActivity extends Activity {

    private static final String TAG = RevisionActivity.class.getName();

    @Inject
    RevisionPresenter mPresenter;

    @Inject
    LogWrapper mLogWrapper;
    @Inject
    GoogleAnalyticsHelper mAnalyticsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revision);

        // TODO: Check for existing fragments
        RevisionFragment revisionFragment = RevisionFragment.newInstance();

        // Create presenters
        DataComponent dataComponent = ((VocabularyApplication)getApplication()).getDataComponent();
        DaggerRevisionComponent.builder()
                .revisionModule(new RevisionModule(revisionFragment))
                .dataComponent(dataComponent)
                .build()
                .inject(this);

        checkNotNull(mPresenter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mAnalyticsHelper.sendScreenEvent(TAG);
        mLogWrapper.logDebug(TAG, "onResume");
    }
}
