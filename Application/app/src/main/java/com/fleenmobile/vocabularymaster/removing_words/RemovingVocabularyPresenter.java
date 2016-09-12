package com.fleenmobile.vocabularymaster.removing_words;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;
import com.fleenmobile.vocabularymaster.removing_words.domain.GetAllVocabularyTask;
import com.fleenmobile.vocabularymaster.removing_words.domain.RemoveVocabularyTask;
import com.fleenmobile.vocabularymaster.removing_words.domain.filter.FilterVocabularyByPatternTask;
import com.google.common.collect.Lists;

import java.util.List;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * @author FleenMobile at 2016-09-09
 */
public class RemovingVocabularyPresenter implements RemovingVocabularyContract.Presenter{

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

    @NonNull
    private VocabularyDataSource mDataSource;

    @Inject
    RemovingVocabularyPresenter(RemovingVocabularyContract.View view, VocabularyDataSource dataSource) {
        mView = view;
        mDataSource = dataSource;

        mSubscriptions = new CompositeSubscription();
    }

    @Inject
    public void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void loadVocabulary() {
        // TODO
    }

    @Override
    public void removeVocabulary() {
        // TODO
    }

    @Override
    public void onVocabularyChosen(Vocabulary vocabulary) {
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
