package com.fleenmobile.vocabularymaster.data;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.model.StatKey;
import com.fleenmobile.vocabularymaster.data.model.Stats;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * @author FleenMobile at 2016-09-10
 */
public class FakeVocabularyDataSource implements VocabularyDataSource {

    private Application mContext;

    private static final Map<Vocabulary, Boolean> VOCABULARY_DATA = Maps.newHashMap();
    private static final boolean LEARNT = true;
    private static final boolean NOT_LEARNT = false;

    public FakeVocabularyDataSource(Application context) {
        mContext = context;
    }

    @Override
    public Observable<List<Vocabulary>> getVocabulary(int amount, int offset) {
        return Observable.from(VOCABULARY_DATA.entrySet())
                .map(Map.Entry::getKey)
                .toList()
                .limit(amount)
                .skip(offset);
    }

    @Override
    public Observable<List<Vocabulary>> getRandomVocabulary(int amount) {
        List<Vocabulary> keys = Observable.from(VOCABULARY_DATA.entrySet())
                .map(Map.Entry::getKey)
                .toList()
                .toBlocking()
                .single();

        Collections.shuffle(keys);

        return Observable
                .from(keys)
                .limit(amount)
                .toList();
    }

    @Override
    public Observable<List<Vocabulary>> getLearntVocabulary(int amount, int offset) {
        return Observable.from(VOCABULARY_DATA.entrySet())
                .filter(entry -> entry.getValue() == LEARNT)
                .map(Map.Entry::getKey)
                .toList()
                .limit(amount)
                .skip(offset);
    }

    @Override
    public Observable<List<Vocabulary>> getLearntVocabularySortedBy(int amount, int offset, @NonNull VocabularySortingStrategy sortedBy) {
        return Observable.from(VOCABULARY_DATA.entrySet())
                .filter(entry -> entry.getValue() == LEARNT)
                .map(Map.Entry::getKey)
                .toSortedList((vocabulary, vocabulary2) -> vocabulary.compare(vocabulary2, sortedBy))
                .limit(amount)
                .skip(offset);
    }

    @Override
    public Observable<Stats> getStats() {
        Map<StatKey, Long> stats = Maps.newHashMap();
        stats.put(StatKey.ALL_WORDS, (long) VOCABULARY_DATA.size());
        stats.put(StatKey.LEARNT_WORDS,
                Long.valueOf(Observable
                        .from(VOCABULARY_DATA.entrySet())
                        .filter(entry -> entry.getValue() == LEARNT)
                        .count()
                        .toBlocking()
                        .first()));

        return Observable.just(new Stats(stats));
    }

    @Override
    public Observable<Vocabulary> addVocabulary(@NonNull Vocabulary vocabulary) {
        VOCABULARY_DATA.put(vocabulary, NOT_LEARNT);
        return Observable.just(vocabulary);
    }

    @Override
    public Observable<List<Vocabulary>> addVocabulary(@NonNull List<Vocabulary> vocabulary) {
        for (Vocabulary item : vocabulary)
            VOCABULARY_DATA.put(item, NOT_LEARNT);

        return Observable.from(vocabulary).toList();
    }

    @Override
    public Observable<List<Vocabulary>> removeVocabulary(@NonNull List<Vocabulary> vocabulary) {
        Observable.from(vocabulary)
                .forEach(item -> VOCABULARY_DATA.remove(findVocabulary(item)));

        return Observable.from(vocabulary).toList();
    }

    @Override
    public void markVocabularyAsLearnt(List<Vocabulary> vocabulary) {
        Observable.from(vocabulary)
                .forEach(item -> VOCABULARY_DATA.put(findVocabulary(item), LEARNT));
    }

    private Vocabulary findVocabulary(Vocabulary item) {
        return Observable.from(VOCABULARY_DATA.entrySet())
                .map(Map.Entry::getKey)
                .filter(entryKey -> entryKey.getID() == item.getID())
                .toBlocking()
                .first();
    }

    @Override
    public void updateVocabulary(Vocabulary vocabulary) {
        Vocabulary oldVocabulary = findVocabulary(vocabulary);
        boolean oldValue = VOCABULARY_DATA.remove(oldVocabulary);
        VOCABULARY_DATA.put(vocabulary, oldValue);
    }
}
