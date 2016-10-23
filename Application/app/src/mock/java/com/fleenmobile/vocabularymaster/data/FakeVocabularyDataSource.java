package com.fleenmobile.vocabularymaster.data;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.fleenmobile.vocabularymaster.data.model.StatKey;
import com.fleenmobile.vocabularymaster.data.model.Stats;
import com.fleenmobile.vocabularymaster.data.model.Translation;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;

import rx.Observable;

/**
 * @author FleenMobile at 2016-09-10
 */
public class FakeVocabularyDataSource implements VocabularyDataSource {

    private Application mContext;

    private static Map<Vocabulary, Boolean> VOCABULARY_DATA = Maps.newLinkedHashMap();
    private static final boolean LEARNT = true;
    private static final boolean NOT_LEARNT = false;

    public FakeVocabularyDataSource(Application context) {
        mContext = context;
        fillOutDB();
    }

    @Override
    public Observable<List<Vocabulary>> getVocabulary(int amount, int offset) {
        return Observable.from(VOCABULARY_DATA.entrySet())
                .map(Map.Entry::getKey)
                .skip(offset)
                .limit(amount)
                .toList();
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
                .skip(offset)
                .limit(amount)
                .toList();
    }

    @Override
    public Observable<List<Vocabulary>> getLearntVocabularySortedBy(int amount, int offset, @NonNull VocabularySortingStrategy sortedBy) {
        return Observable.from(VOCABULARY_DATA.entrySet())
                .filter(entry -> entry.getValue() == LEARNT)
                .map(Map.Entry::getKey)
                .toSortedList((vocabulary, vocabulary2) -> vocabulary.compare(vocabulary2, sortedBy))
                .flatMapIterable(item -> item)
                .skip(offset)
                .limit(amount)
                .toList();
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
        Boolean result;
        if (vocabulary.getID() == -1L) {
            // Vocabulary is not fully initialized yet (it only has word and one translation as it
            // was added through user's input
            Vocabulary vocabularyWithTheSameWord = lookForVocabulary(vocabulary.getWord());

            if (vocabularyWithTheSameWord != null) {
                boolean learnt = VOCABULARY_DATA.get(vocabularyWithTheSameWord);
                // We already has this vocabulary. Just need to update it's list of translations
                vocabularyWithTheSameWord.getTranslations().addAll(vocabulary.getTranslations());
                result = VOCABULARY_DATA.put(vocabularyWithTheSameWord, learnt);
            } else {
                // We need to add new vocabulary
                vocabulary.setID(VOCABULARY_DATA.size());
                vocabulary.setTotalCorrectTries(0);
                vocabulary.setTotalIncorrectTries(0);
                result = VOCABULARY_DATA.put(vocabulary, NOT_LEARNT);
            }
        } else {
            result = VOCABULARY_DATA.put(vocabulary, NOT_LEARNT);
        }

        if (result == null)
            return Observable.just(vocabulary);
        else
            return Observable.empty();
    }

    private Vocabulary lookForVocabulary(String word) {
        return Observable.from(VOCABULARY_DATA.entrySet())
                .filter(entry -> entry.getKey().getWord().toLowerCase().equals(word.toLowerCase()))
                .map(Map.Entry::getKey)
                .toBlocking()
                .first();
    }

    @Override
    public Observable<List<Vocabulary>> addVocabulary(@NonNull List<Vocabulary> vocabulary) {
        List<Vocabulary> added = Lists.newArrayList();
        for (Vocabulary item : vocabulary) {
            if (!addVocabulary(item).isEmpty().toBlocking().first())
                added.add(item);
        }

        return Observable.from(added).toList();
    }

    @Override
    public Observable<List<Vocabulary>> removeVocabulary(@NonNull List<Vocabulary> vocabulary) {
        List<Vocabulary> removed = Lists.newArrayList();
        Observable.from(vocabulary)
                .forEach(item -> {
                            if (VOCABULARY_DATA.remove(findVocabulary(item)) != null)
                                removed.add(item);
                        }
                        ,
                        error -> {
                            // We don't have this element in DB
                        }
                );

        return Observable.from(removed).toList();
    }

    @Override
    public void markVocabularyAsLearnt(List<Vocabulary> vocabulary) {
        Observable.from(vocabulary)
                .forEach(item -> VOCABULARY_DATA.put(findVocabulary(item), LEARNT)
                        ,
                        error -> {
                            // We don't have this element in DB
                        });
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
        try {
            Vocabulary oldVocabulary = findVocabulary(vocabulary);
            boolean oldValue = VOCABULARY_DATA.remove(oldVocabulary);
            VOCABULARY_DATA.put(vocabulary, oldValue);
        } catch (NoSuchElementException e) {
            // We don't have this vocabulary in DB
        }
    }

    @VisibleForTesting
    public void clearDatabase() {
        VOCABULARY_DATA = Maps.newHashMap();
    }

    private void fillOutDB() {
        Vocabulary tempVocabulary;
        List<Translation> tempTranslations;
        int tempTotalCorrectTries, tempTotalIncorrectTries, tempCorrectTries, tempIncorrectTries;

        Random r = new Random(Calendar.getInstance().getTimeInMillis());
        int amount = 100;

        for (int i = 0; i < amount; i++) {
            tempTranslations = Lists.newArrayList();
            tempTotalCorrectTries = 0;
            tempTotalIncorrectTries = 0;

            for (int translationCount = 0; translationCount < r.nextInt(4) + 1; translationCount++) {
                tempCorrectTries = r.nextInt(200);
                tempIncorrectTries = r.nextInt(200);
                tempTotalCorrectTries += tempCorrectTries;
                tempTotalIncorrectTries += tempIncorrectTries;

                tempTranslations.add(
                        new Translation(String.format("word%strans%s", i, translationCount),
                                r.nextBoolean(),
                                tempCorrectTries,
                                tempIncorrectTries
                        )
                );
            }

            tempVocabulary = new Vocabulary(
                    i,
                    String.format("word%s", i),
                    tempTranslations,
                    tempTotalCorrectTries,
                    tempTotalIncorrectTries
            );

            addVocabulary(tempVocabulary);

            if (r.nextBoolean())
                markVocabularyAsLearnt(Lists.newArrayList(tempVocabulary));
        }
    }
}
