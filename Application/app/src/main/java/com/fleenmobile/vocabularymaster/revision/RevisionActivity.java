package com.fleenmobile.vocabularymaster.revision;

import android.app.Activity;
import android.os.Bundle;

import com.fleenmobile.vocabularymaster.R;
import com.fleenmobile.vocabularymaster.application.VocabularyApplication;
import com.fleenmobile.vocabularymaster.data.di.DataComponent;
import com.fleenmobile.vocabularymaster.revision.di.DaggerRevisionComponent;
import com.fleenmobile.vocabularymaster.revision.di.RevisionModule;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class RevisionActivity extends Activity {

    @Inject
    RevisionPresenter mPresenter;

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
}
