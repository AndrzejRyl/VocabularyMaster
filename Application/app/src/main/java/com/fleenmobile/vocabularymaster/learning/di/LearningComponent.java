package com.fleenmobile.vocabularymaster.learning.di;

import com.fleenmobile.vocabularymaster.data.di.DataComponent;
import com.fleenmobile.vocabularymaster.learning.LearningActivity;

import dagger.Component;

/**
 * @author FleenMobile at 2016-09-12
 */
@LearningScope
@Component(dependencies = DataComponent.class, modules = {LearningModule.class})
public interface LearningComponent {

    void inject(LearningActivity learningActivity);
}
