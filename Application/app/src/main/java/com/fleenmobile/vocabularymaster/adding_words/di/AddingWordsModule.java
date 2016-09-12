package com.fleenmobile.vocabularymaster.adding_words.di;

import com.fleenmobile.vocabularymaster.adding_words.AddFilePopupContract;
import com.fleenmobile.vocabularymaster.adding_words.AddOneVocabularyPopupContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author FleenMobile at 2016-09-12
 */
@Module
public class AddingWordsModule {

    private final AddFilePopupContract.View mAddFileView;
    private final AddOneVocabularyPopupContract.View mAddOneVocabularyView;

    public AddingWordsModule(AddFilePopupContract.View addFileView, AddOneVocabularyPopupContract.View addOneVocabularyView) {
        this.mAddFileView = addFileView;
        this.mAddOneVocabularyView = addOneVocabularyView;
    }

    @Provides
    AddFilePopupContract.View provideAddFilePopupView() {
        return mAddFileView;
    }

    @Provides
    AddOneVocabularyPopupContract.View provideAddOneVocabularyPopupView() {
        return mAddOneVocabularyView;
    }
}
