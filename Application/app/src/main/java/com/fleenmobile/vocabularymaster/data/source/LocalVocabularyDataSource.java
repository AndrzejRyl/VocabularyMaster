package com.fleenmobile.vocabularymaster.data.source;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.fleenmobile.vocabularymaster.data.VocabularySortingStrategy;
import com.fleenmobile.vocabularymaster.data.model.Stats;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;

import java.util.List;

import rx.Observable;

/**
 * @author FleenMobile at 2016-09-10
 */
public class LocalVocabularyDataSource implements VocabularyDataSource{

    private Application mContext;

    public LocalVocabularyDataSource(Application context) {
        mContext = context;
    }

    @Override
    public Observable<List<Vocabulary>> getVocabulary(int amount, int offset) {
        // TODO
        return null;
    }

    @Override
    public Observable<List<Vocabulary>> getRandomVocabulary(int amount) {
        // TODO
        return null;
    }

    @Override
    public Observable<List<Vocabulary>> getLearntVocabulary(int amount, int offset) {
        // TODO
        return null;
    }

    @Override
    public Observable<List<Vocabulary>> getLearntVocabularySortedBy(int amount, int offset, @NonNull VocabularySortingStrategy sortedBy) {
        // TODO
        return null;
    }

    @Override
    public Observable<Stats> getStats() {
        // TODO
        return null;
    }

    @Override
    public Observable<Vocabulary> addVocabulary(@NonNull Vocabulary vocabulary) {
        // TODO
        return null;
    }

    @Override
    public Observable<List<Vocabulary>> addVocabulary(@NonNull List<Vocabulary> vocabulary) {
        // TODO
        return null;
    }

    @Override
    public Observable<List<Vocabulary>> removeVocabulary(@NonNull List<Vocabulary> vocabulary) {
        // TODO
        return null;
    }

    @Override
    public void markVocabularyAsLearnt(List<Vocabulary> vocabulary) {
        // TODO
    }

    @Override
    public void updateVocabulary(Vocabulary vocabulary) {
        // TODO
    }

    @VisibleForTesting
    @Override
    public void clearDatabase() {
        // TODO
    }
}
