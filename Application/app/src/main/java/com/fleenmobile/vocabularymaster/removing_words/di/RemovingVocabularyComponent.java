package com.fleenmobile.vocabularymaster.removing_words.di;

import com.fleenmobile.vocabularymaster.data.di.DataComponent;
import com.fleenmobile.vocabularymaster.removing_words.RemovingVocabularyActivity;

import dagger.Component;

/**
 * @author FleenMobile at 2016-09-12
 */
@RemovingVocabularyScope
@Component(dependencies = DataComponent.class, modules = {RemovingVocabularyModule.class})
public interface RemovingVocabularyComponent {

    void inject(RemovingVocabularyActivity removingVocabularyActivity);
}
