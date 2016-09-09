package com.fleenmobile.vocabularymaster.removing_words;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.removing_words.domain.GetAllVocabularyTask;
import com.fleenmobile.vocabularymaster.removing_words.domain.RemoveVocabularyTask;
import com.fleenmobile.vocabularymaster.removing_words.domain.filter.FilterVocabularyByPatternTask;
import com.fleenmobile.vocabularymaster.revision.RevisionContract;
import com.google.common.collect.Lists;

import java.util.List;

import rx.subscriptions.CompositeSubscription;

/**
 * @author FleenMobile at 2016-09-09
 */
public class RemovingVocabularyPresenter implements RevisionContract.Presenter{

    @NonNull
    private RemovingVocabularyContract.View mView;
    @NonNull
    private List<Vocabulary> mVocabularyToRemove = Lists.newArrayList();

    @NonNull
    private GetAllVocabularyTask mGetVocabulary;
    @NonNull
    private FilterVocabularyByPatternTask mFilterVocabulary;
    @NonNull
    private RemoveVocabularyTask mRemoveVocabulary;

    @NonNull
    private CompositeSubscription mSubscriptions;

    @Override
    public void loadVocabulary() {
        // TODO
    }

    @Override
    public void updateVocabulary(Vocabulary vocabulary) {
        // TODO
    }

    @Override
    public void onNewVocabularyChosen(Vocabulary vocabulary) {
        // TODO
    }

    @Override
    public void checkTranslations(List<String> translations) {
        // TODO
    }

    @Override
    public void subscribe() {
        // TODO
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
