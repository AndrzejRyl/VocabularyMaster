package com.fleenmobile.vocabularymaster.data.source;

import android.app.Application;

import com.fleenmobile.vocabularymaster.BuildConfig;
import com.fleenmobile.vocabularymaster.data.FakeVocabularyDataSource;
import com.fleenmobile.vocabularymaster.data.VocabularySortingStrategy;
import com.fleenmobile.vocabularymaster.data.model.StatKey;
import com.fleenmobile.vocabularymaster.data.model.Stats;
import com.fleenmobile.vocabularymaster.data.model.Translation;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author FleenMobile at 2016-09-15
 */
public class VocabularyDataSourceTest {

    @Mock
    private Application mApplication;
    private VocabularyDataSource mVocabularyDataSource;
    private TestSubscriber<List<Vocabulary>> mVocabularyListTestSubscriber;
    private TestSubscriber<Vocabulary> mVocabularyTestSubscriber;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mVocabularyDataSource = (BuildConfig.FLAVOR.equals("mock") ? new FakeVocabularyDataSource(mApplication) : new LocalVocabularyDataSource(mApplication));

        mVocabularyTestSubscriber = new TestSubscriber<>();
        mVocabularyListTestSubscriber = new TestSubscriber<>();
        mVocabularyDataSource.clearDatabase();
    }

    @Test
    public void addVocabulary_vocabularyIsSaved_whenAddingOneVocabulary() {
        // ================ GIVEN ================
        // Data source doesn't have this vocabulary already (clearing DB is in @Before method)
        Vocabulary vocabularyToAdd = mVocabularyToAdd.get(0);

        // ================ WHEN ================
        // Adding one vocabulary
        Vocabulary result = mVocabularyDataSource.addVocabulary(vocabularyToAdd).toBlocking().first();

        // ================ THEN ================
        // This vocabulary is indeed added to database and available for querying
        // Retrieve first vocabulary in DB
        List<Vocabulary> vocabularyInDB = mVocabularyDataSource.getVocabulary(1, 0).toBlocking().first();
        // Assert it's the one added in this test
        assertTrue(listContainsVocabulary(vocabularyInDB, vocabularyToAdd));
        // Assert added vocabulary was returned
        assertEquals(vocabularyToAdd.getID(), result.getID());
    }

    @Test
    public void addVocabulary_vocabularyIsNotDuplicated_whenAddedAlreadyAddedVocabulary() {
        // ================ GIVEN ================
        // Data source already has added vocabulary
        Vocabulary vocabularyToAdd = mVocabularyToAdd.get(0);
        mVocabularyDataSource.addVocabulary(vocabularyToAdd);

        // ================ WHEN ================
        // Trying to add the same vocabulary again
        Observable<Vocabulary> result = mVocabularyDataSource.addVocabulary(vocabularyToAdd);

        // ================ THEN ================
        // There is only one vocabulary with that ID
        // Retrieve vocabulary from DB (is there is a duplicate we have 2 vocabulary in DB)
        List<Vocabulary> vocabularyInDB = mVocabularyDataSource.getVocabulary(2, 0).toBlocking().first();
        // Assert there is only one vocabulary in DB (no duplicates)
        assertTrue(listContainsVocabulary(vocabularyInDB, vocabularyToAdd));
        // Assert that nothing was returned
        assertTrue(result.isEmpty().toBlocking().first());
    }

    @Test
    public void addVocabulary_vocabularyIsAddedAsNotLearnt_whenAddingOneVocabulary() {
        // ================ GIVEN ================
        Vocabulary vocabularyToAdd = mVocabularyToAdd.get(0);

        // ================ WHEN ================
        // Adding vocabulary to DB
        mVocabularyDataSource.addVocabulary(vocabularyToAdd);

        // ================ THEN ================
        // It's added with NOT_LEARNT flag
        List<Vocabulary> learntVocabulary = mVocabularyDataSource.getLearntVocabulary(1, 0).toBlocking().first();
        List<Vocabulary> allVocabulary = mVocabularyDataSource.getVocabulary(1, 0).toBlocking().first();

        assertEquals(learntVocabulary.size(), 0);
        assertTrue(listContainsVocabulary(allVocabulary, vocabularyToAdd));
    }

    @Test
    public void addVocabulary_vocabularyIsSaved_whenAddingListOfVocabulary() {
        // ================ GIVEN ================
        int subsetAmount = 3;
        // Data source doesn't have this vocabulary already (clearing DB is in @Before method)
        List<Vocabulary> vocabularyToAdd = mVocabularyToAdd.subList(0, subsetAmount);

        // ================ WHEN ================
        // Adding list of vocabulary
        List<Vocabulary> result = mVocabularyDataSource.addVocabulary(vocabularyToAdd).toBlocking().first();

        // ================ THEN ================
        // This vocabulary is indeed added to database and available for querying
        // Retrieve first 3 vocabulary in DB
        List<Vocabulary> vocabularyInDB = mVocabularyDataSource.getVocabulary(subsetAmount, 0).toBlocking().first();

        assertTrue(listContainsVocabulary(vocabularyInDB, vocabularyToAdd));
        assertTrue(listContainsVocabulary(result, vocabularyToAdd));
    }

    @Test
    public void addVocabulary_vocabularyIsNotDuplicated_whenAddedAlreadyAddedListOfVocabulary() {
        // ================ GIVEN ================
        int subsetAmount = 3;
        // Data source already has added vocabulary
        List<Vocabulary> vocabularyToAdd = mVocabularyToAdd.subList(0, subsetAmount);
        mVocabularyDataSource.addVocabulary(vocabularyToAdd);

        // ================ WHEN ================
        // Trying to add the same vocabulary again
        List<Vocabulary> result = mVocabularyDataSource.addVocabulary(vocabularyToAdd).toBlocking().first();

        // ================ THEN ================
        // There is only one vocabulary with that ID
        // Retrieve vocabulary from DB (is there is a duplicate we have 6 vocabulary in DB)
        List<Vocabulary> vocabularyInDB = mVocabularyDataSource.getVocabulary(subsetAmount * 2, 0).toBlocking().first();
        // Assert there are no duplicates
        assertTrue(listContainsVocabulary(vocabularyInDB, vocabularyToAdd));
        // Assert nothing was returned
        assertEquals(result.size(), 0);
    }

    @Test
    public void addVocabulary_vocabularyIsAddedAsNotLearnt_whenAddingListOfVocabulary() {
        // ================ GIVEN ================
        int subsetAmount = 3;
        List<Vocabulary> vocabularyToAdd = mVocabularyToAdd.subList(0, subsetAmount);

        // ================ WHEN ================
        // Adding vocabulary to DB
        mVocabularyDataSource.addVocabulary(vocabularyToAdd);

        // ================ THEN ================
        // It's added with NOT_LEARNT flag
        List<Vocabulary> learntVocabulary = mVocabularyDataSource.getLearntVocabulary(subsetAmount, 0).toBlocking().first();
        List<Vocabulary> allVocabulary = mVocabularyDataSource.getVocabulary(subsetAmount, 0).toBlocking().first();

        assertEquals(learntVocabulary.size(), 0);
        assertTrue(listContainsVocabulary(allVocabulary, vocabularyToAdd));
    }

    @Test
    public void getVocabulary_returnsEmptyList_whenNoVocabularyIsAdded() {
        // ================ GIVEN ================
        // No vocabulary is added (clearDatabase method is called in @Before method)

        // ================ WHEN ================
        // Retrieving vocabulary from DB
        List<Vocabulary> result = mVocabularyDataSource.getVocabulary(1, 0).toBlocking().first();

        // ================ THEN ================
        // List has to be empty and no error is thrown
        assertEquals(result.size(), 0);
    }

    @Test
    public void getRandomVocabulary_returnsShuffledCollection_whenAddedMoreThanOneVocabulary() {
        // ================ GIVEN ================

        // ================ WHEN ================
        // Adding more than one vocabulary
        mVocabularyDataSource.addVocabulary(mVocabularyToAdd).subscribe();
        List<Vocabulary> vocabularyInDB;

        // ================ THEN ================
        // Assert that there are not in the same order
        boolean inTheSameOrder = true;

        int retryCount = 10;
        // There is a possibility that shuffling somehow will return exactly the same collection
        // That's why we have to repeat this test
        for (int repeat = 0; repeat < retryCount && inTheSameOrder; repeat++) {
            vocabularyInDB = mVocabularyDataSource.getRandomVocabulary(mVocabularyToAdd.size()).toBlocking().first();

            for (int i = 0; i < vocabularyInDB.size(); i++)
                if (vocabularyInDB.get(i).getID() != mVocabularyToAdd.get(i).getID())
                    inTheSameOrder = false;
        }

        assertFalse(inTheSameOrder);
    }

    @Test
    public void markVocabularyAsLearnt_marksVocabularyInDB() {
        // ================ GIVEN ================
        // Database has one vocabulary
        Vocabulary vocabularyToAdd = mVocabularyToAdd.get(0);
        mVocabularyDataSource.addVocabulary(vocabularyToAdd);

        // ================ WHEN ================
        // Marking this vocabulary as learnt
        mVocabularyDataSource.markVocabularyAsLearnt(Lists.newArrayList(vocabularyToAdd));

        // ================ THEN ================
        // It's saved in DB as learnt
        List<Vocabulary> vocabularyInDB = mVocabularyDataSource.getLearntVocabulary(1, 0).toBlocking().first();
        assertTrue(listContainsVocabulary(vocabularyInDB, vocabularyToAdd));
    }

    @Test
    public void getLearntVocabularySortedBy_returnsCorrectlySortedCollection() {
        // ================ GIVEN ================
        int subsetAmount = 3;
        // Adding vocabulary with different correct tries percentage and marking them as learnt
        List<Vocabulary> vocabularyToAdd = mVocabularyToAdd.subList(0, subsetAmount);
        VocabularySortingStrategy sortByCorrectTriesPerc = VocabularySortingStrategy.SORT_BY_CORRECT_TRIES_PERC;
        VocabularySortingStrategy sortByCorrectTriesPercDesc = VocabularySortingStrategy.SORT_BY_CORRECT_TRIES_PERC_DESC;
        mVocabularyDataSource.addVocabulary(vocabularyToAdd);
        mVocabularyDataSource.markVocabularyAsLearnt(vocabularyToAdd);

        // ================ WHEN ================
        // Retrieving vocabulary with sorting strategy
        List<Vocabulary> sortedByCorrectTries = mVocabularyDataSource.getLearntVocabularySortedBy(subsetAmount, 0, sortByCorrectTriesPerc).toBlocking().first();
        List<Vocabulary> sortedByCorrectTriesDesc = mVocabularyDataSource.getLearntVocabularySortedBy(subsetAmount, 0, sortByCorrectTriesPercDesc).toBlocking().first();

        Observable<Long> sortedByCorrectTriesIdices = Observable
                .from(sortedByCorrectTries)
                .map(Vocabulary::getID);

        Observable<Long> sortedByCorrectTriesDescIdices = Observable
                .from(sortedByCorrectTriesDesc)
                .map(Vocabulary::getID);

        // ================ THEN ================
        // They have correct order
        assertTrue(Iterables.elementsEqual(sortedByCorrectTriesIdices.toBlocking().toIterable(), Lists.newArrayList(3L, 1L, 2L)));
        assertTrue(Iterables.elementsEqual(sortedByCorrectTriesDescIdices.toBlocking().toIterable(), Lists.newArrayList(2L, 1L, 3L)));
    }

    @Test
    public void getStats_updatesStats_whenAddingVocabulary() {
        // ================ GIVEN ================
        // Database is empty (clearDatabase method is called in @Before method)
        Vocabulary vocabularyToAdd = mVocabularyToAdd.get(0);

        // ================ WHEN ================
        // Adding vocabulary
        mVocabularyDataSource.addVocabulary(vocabularyToAdd);

        // ================ THEN ================
        // Stats of all vocabulary is updated
        Stats stats = mVocabularyDataSource.getStats().toBlocking().first();
        assertEquals(stats.getmStats().get(StatKey.ALL_WORDS).longValue(), 1L);
    }

    @Test
    public void getStats_updatesStats_whenMarkingVocabularyAsLearnt() {
        // ================ GIVEN ================
        // Database is empty (clearDatabase method is called in @Before method)
        Vocabulary vocabularyToAdd = mVocabularyToAdd.get(0);

        // ================ WHEN ================
        // Adding vocabulary
        mVocabularyDataSource.addVocabulary(vocabularyToAdd);
        mVocabularyDataSource.markVocabularyAsLearnt(Lists.newArrayList(vocabularyToAdd));

        // ================ THEN ================
        // Stats of all vocabulary is updated
        Stats stats = mVocabularyDataSource.getStats().toBlocking().first();
        assertEquals(stats.getmStats().get(StatKey.LEARNT_WORDS).longValue(), 1L);
    }

    @Test
    public void getStats_returnsZero_WhenNoVocabularyInDB() {
        // ================ GIVEN ================
        // No vocabulary in DB (clearDatabase method was called in @Bfore method)

        // ================ WHEN ================
        long allVocabulary = mVocabularyDataSource.getStats().toBlocking().first().getmStats().get(StatKey.ALL_WORDS);
        long learntVocabulary = mVocabularyDataSource.getStats().toBlocking().first().getmStats().get(StatKey.LEARNT_WORDS);

        // ================ THEN ================
        assertEquals(allVocabulary, 0L);
        assertEquals(learntVocabulary, 0L);
    }

    @Test
    public void removeVocabulary_removesVocabularyFromDB_whenExisting() {
        // ================ GIVEN ================
        // There is a vocabulary in DB
        mVocabularyDataSource.addVocabulary(mVocabularyToAdd);

        // ================ WHEN ================
        // Removing existing vocabulary
        List<Vocabulary> result = mVocabularyDataSource.removeVocabulary(mVocabularyToAdd).toBlocking().first();

        // ================ THEN ================
        // Assert that removed vocabulary is returned
        assertTrue(listContainsVocabulary(result, mVocabularyToAdd));
        // Assert that it's not in DB anymore
        assertEquals(mVocabularyDataSource.getVocabulary(1, 0).toBlocking().first().size(), 0);
    }

    @Test
    public void removeVocabulary_doesNotCrash_whenTryingToRemoveFromEmptyDB() {
        // ================ GIVEN ================
        // Database is empty (clearDatabase method was called in @Before method)

        // ================ WHEN ================
        // Removing vocabulary from DB
        Observable<List<Vocabulary>> result = mVocabularyDataSource.removeVocabulary(mVocabularyToAdd);

        // ================ THEN ================
        // Assert that result and DB are empty
        assertEquals(mVocabularyDataSource.getVocabulary(1, 0).toBlocking().first().size(), 0);
        assertEquals(result.toBlocking().first().size(), 0);
    }

    @Test
    public void updateVocabulary_correctlyUpdates_whenExisting() {
        // ================ GIVEN ================
        // There is vocabulary in DB
        Vocabulary vocabularyToAdd = mVocabularyToAdd.get(0);
        mVocabularyDataSource.addVocabulary(vocabularyToAdd);

        // ================ WHEN ================
        // Updating vocabulary
        vocabularyToAdd.setWord("Update test");
        mVocabularyDataSource.updateVocabulary(vocabularyToAdd);

        // ================ THEN ================
        // Assert that name has changed
        assertEquals(mVocabularyDataSource.getVocabulary(1, 0).toBlocking().first().get(0).getWord(), "Update test");
    }

    @Test
    public void updateVocabulary_doesNotCrash_whenTryingToUpdateNonExistingVocabulary() {
        // ================ GIVEN ================
        // Database is empty (clear database method was called in @Before method)

        // ================ WHEN ================
        // Trying to update non existing vocabulary
        mVocabularyDataSource.updateVocabulary(mVocabularyToAdd.get(0));

        // ================ THEN ================
        // Assert that database is still empty
        assertEquals(mVocabularyDataSource.getVocabulary(1, 0).toBlocking().first().size(), 0);
    }

    private boolean listContainsVocabulary(List<Vocabulary> vocabularyList, Vocabulary searchedVocabulary) {
        return !Observable.from(vocabularyList).filter(vocabulary -> vocabulary.getID() == searchedVocabulary.getID()).isEmpty().toBlocking().first();
    }


    private boolean listContainsVocabulary(List<Vocabulary> vocabularyList, List<Vocabulary> searchedVocabulary) {
        boolean result = true;

        for (Vocabulary vocabulary : searchedVocabulary)
            result = (listContainsVocabulary(vocabularyList, vocabulary)) && result;

        return result;
    }

    private static List<Vocabulary> mVocabularyToAdd = Lists.newArrayList(
            new Vocabulary(
                    1,
                    "Word1",
                    Lists.newArrayList(
                            new Translation(
                                    "word1translation1",
                                    true,
                                    2,
                                    0),
                            new Translation(
                                    "word1translation2",
                                    true,
                                    6,
                                    0),
                            new Translation(
                                    "word1translation3",
                                    true,
                                    4,
                                    2)), 12, 2),
            new Vocabulary(
                    2,
                    "Word2",
                    Lists.newArrayList(
                            new Translation(
                                    "word2translation1",
                                    true,
                                    2,
                                    0),
                            new Translation(
                                    "word2translation2",
                                    true,
                                    6,
                                    0),
                            new Translation(
                                    "word2translation3",
                                    false,
                                    0,
                                    0)), 8, 0),
            new Vocabulary(
                    3,
                    "Word3",
                    Lists.newArrayList(
                            new Translation(
                                    "word3translation1",
                                    true,
                                    2,
                                    0)), 2, 1),
            new Vocabulary(
                    4,
                    "Word4",
                    Lists.newArrayList(
                            new Translation(
                                    "word4translation1",
                                    true,
                                    2,
                                    0),
                            new Translation(
                                    "word4translation2",
                                    false,
                                    0,
                                    0),
                            new Translation(
                                    "word4translation3",
                                    false,
                                    0,
                                    0)), 2, 0),

            new Vocabulary(
                    5,
                    "Word5",
                    Lists.newArrayList(
                            new Translation(
                                    "word5translation1",
                                    true,
                                    2,
                                    0),
                            new Translation(
                                    "word5translation2",
                                    true,
                                    6,
                                    0),
                            new Translation(
                                    "word5translation3",
                                    true,
                                    4,
                                    2)), 12, 2),
            new Vocabulary(
                    6,
                    "Word6",
                    Lists.newArrayList(
                            new Translation(
                                    "word6translation1",
                                    true,
                                    2,
                                    0),
                            new Translation(
                                    "word6translation2",
                                    true,
                                    6,
                                    0),
                            new Translation(
                                    "word6translation3",
                                    false,
                                    0,
                                    0)), 8, 0),
            new Vocabulary(
                    7,
                    "Word7",
                    Lists.newArrayList(
                            new Translation(
                                    "word7translation1",
                                    true,
                                    2,
                                    0)), 2, 0),
            new Vocabulary(
                    8,
                    "Word8",
                    Lists.newArrayList(
                            new Translation(
                                    "word8translation1",
                                    true,
                                    2,
                                    0),
                            new Translation(
                                    "word8translation2",
                                    false,
                                    0,
                                    0),
                            new Translation(
                                    "word8translation3",
                                    false,
                                    0,
                                    0)), 2, 0)
    );

}
