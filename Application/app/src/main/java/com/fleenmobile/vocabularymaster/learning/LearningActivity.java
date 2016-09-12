package com.fleenmobile.vocabularymaster.learning;

import android.app.Activity;
import android.os.Bundle;

import com.fleenmobile.vocabularymaster.R;
import com.fleenmobile.vocabularymaster.application.VocabularyApplication;
import com.fleenmobile.vocabularymaster.data.di.DataComponent;
import com.fleenmobile.vocabularymaster.learning.di.DaggerLearningComponent;
import com.fleenmobile.vocabularymaster.learning.di.LearningModule;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public class LearningActivity extends Activity {

    @Inject
    LearningPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        // TODO : check for existing fragments
        LearningFragment learningFragment = LearningFragment.newInstance();

        // Create presenter
        DataComponent dataComponent = ((VocabularyApplication) getApplication()).getDataComponent();
        DaggerLearningComponent.builder()
                .learningModule(new LearningModule(learningFragment))
                .dataComponent(dataComponent)
                .build()
                .inject(this);

        checkNotNull(mPresenter);
    }
}
