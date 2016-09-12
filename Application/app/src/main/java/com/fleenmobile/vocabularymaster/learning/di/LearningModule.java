package com.fleenmobile.vocabularymaster.learning.di;

import com.fleenmobile.vocabularymaster.learning.LearningContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author FleenMobile at 2016-09-12
 */
@Module
public class LearningModule {

    private LearningContract.View mView;

    public LearningModule(LearningContract.View view) {
        this.mView = view;
    }

    @Provides
    public LearningContract.View provideLearningView() {
        return mView;
    }
}
