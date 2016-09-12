package com.fleenmobile.vocabularymaster.removing_words.di;

import com.fleenmobile.vocabularymaster.removing_words.RemoveVocabularyConfirmationPopupContract;
import com.fleenmobile.vocabularymaster.removing_words.RemovingVocabularyContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author FleenMobile at 2016-09-12
 */
@Module
public class RemovingVocabularyModule {

    private RemovingVocabularyContract.View mView;
    private RemoveVocabularyConfirmationPopupContract.View mConfirmationView;

    public RemovingVocabularyModule(RemovingVocabularyContract.View view, RemoveVocabularyConfirmationPopupContract.View confirmationView) {
        mView = view;
        mConfirmationView = confirmationView;
    }

    @Provides
    public RemovingVocabularyContract.View provideRemovingWordsView() { return mView; }

    @Provides
    public RemoveVocabularyConfirmationPopupContract.View provideConfirmationView() { return mConfirmationView; }
}
