package com.fleenmobile.vocabularymaster.adding_words.di;

import com.fleenmobile.vocabularymaster.BuyVocabularyPopupContract;
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
    private final BuyVocabularyPopupContract.View mBuyVocabualaryView;

    public AddingWordsModule(AddFilePopupContract.View addFileView, AddOneVocabularyPopupContract.View addOneVocabularyView, BuyVocabularyPopupContract.View buyVocabularyView) {
        this.mAddFileView = addFileView;
        this.mAddOneVocabularyView = addOneVocabularyView;
        this.mBuyVocabualaryView = buyVocabularyView;
    }

    @Provides
    AddFilePopupContract.View provideAddFilePopupView() {
        return mAddFileView;
    }

    @Provides
    AddOneVocabularyPopupContract.View provideAddOneVocabularyPopupView() {
        return mAddOneVocabularyView;
    }

    @Provides
    BuyVocabularyPopupContract.View provideBuyVocabularyPopupView() {
        return mBuyVocabualaryView;
    }
}
