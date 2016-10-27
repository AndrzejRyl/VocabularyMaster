package com.fleenmobile.vocabularymaster.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.fleenmobile.vocabularymaster.data.VocabularySortingStrategy;
import com.fleenmobile.vocabularymaster.data.model.Stats;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;

import java.util.List;

import rx.Observable;

/**
 *
 * Main data source for all vocabulary related data.
 * For now there is only a local implementation using SQLite database
 * but REST API is in plans for future.
 *
 * @author FleenMobile at 2016-09-05
 */
public interface VocabularyDataSource {

    /**
     * Return list of vocabulary without any preconditions
     * @param amount Return at most this many vocabulary
     * @param offset Omit this many vocabulary from the top
     * @return
     */
    Observable<List<Vocabulary>> getVocabulary(int amount, int offset);

    /**
     * Return list of totally random vocabulary (only from the ones that haven't been
     * learnt yet).
     * @param amount Return at most this many vocabulary
     * @return
     */
    Observable<List<Vocabulary>> getRandomVocabulary(int amount);

    /**
     * Return list of vocabulary from the ones that have been learnt already
     * @param amount Return at most this many vocabulary
     * @param offset Omit this many from the top
     * @return
     */
    Observable<List<Vocabulary>> getLearntVocabulary(int amount, int offset);

    /**
     * Return list of vocabulary from the ones that have been learnt already
     * @param amount Return at most this many vocabulary
     * @param offset Omit this many from the top
     * @param sortedBy Strategy based on which this vocabulary is sorted
     * @return
     */
    Observable<List<Vocabulary>> getLearntVocabularySortedBy(int amount, int offset, @NonNull VocabularySortingStrategy sortedBy);

    /**
     * Return list of basic stats about how the user is learning.
     * @return Map of pairs <StatKey, Long>
     */
    Observable<Stats> getStats();

    /**
     * Add one vocabulary to the database
     * @param vocabulary Vocabulary to be added
     * @return This vocabulary if it has been added or null if there was an error
     */
    Observable<Vocabulary> addVocabulary(@NonNull Vocabulary vocabulary);

    /**
     * Add list of vocabulary to the database
     * @param vocabulary Vocabulary to be added
     * @return List of all vocabulary that has been successfully added
     */
    Observable<List<Vocabulary>> addVocabulary(@NonNull List<Vocabulary> vocabulary);

    /**
     * Remove list of vocabulary from the database
     * @param vocabulary Vocabulary to be removed
     * @return List of all vocabulary that has been successfully removed
     */
    Observable<List<Vocabulary>> removeVocabulary(@NonNull List<Vocabulary> vocabulary);

    /**
     * Set in database that this vocabulary has been learnt by a user
     * @param vocabulary
     */
    void markVocabularyAsLearnt(List<Vocabulary> vocabulary);

    /**
     * Update a record in database connected to that vocabulary
     * @param vocabulary
     */
    void updateVocabulary(Vocabulary vocabulary);

    /**
     * This method is needed only for testing. It clears all vocabulary from data source
     */
    @VisibleForTesting
    void clearDatabase();

    /**
     * This method is only for testing. It fills DB with mock data
     */
    @VisibleForTesting
    void fillOutDB();
}
