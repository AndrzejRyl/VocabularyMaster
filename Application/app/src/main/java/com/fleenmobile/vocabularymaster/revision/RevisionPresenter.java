package com.fleenmobile.vocabularymaster.revision;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;
import com.fleenmobile.vocabularymaster.revision.domain.GetVocabularyToReviseTask;
import com.fleenmobile.vocabularymaster.revision.domain.UpdateVocabularyTask;
import com.google.common.collect.Lists;

import java.util.List;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * @author FleenMobile at 2016-09-07
 */
public class RevisionPresenter implements RevisionContract.Presenter {

    @NonNull
    private RevisionContract.View mView;

    @NonNull
    private GetVocabularyToReviseTask mGetVocabulary;
    @NonNull
    private UpdateVocabularyTask mUdpateVocabulary;

    private long mCurrentVocabularyIdx;
    private List<Vocabulary> mVocabulary = Lists.newArrayList();

    @NonNull
    private CompositeSubscription mSubscriptions;

    @NonNull
    private VocabularyDataSource mDataSource;

    @Inject
    RevisionPresenter(RevisionContract.View view, VocabularyDataSource dataSource) {
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
