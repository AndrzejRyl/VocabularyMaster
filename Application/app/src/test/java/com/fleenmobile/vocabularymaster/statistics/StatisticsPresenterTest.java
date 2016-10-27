package com.fleenmobile.vocabularymaster.statistics;

import android.app.Application;

import com.fleenmobile.vocabularymaster.data.VocabularySortingStrategy;
import com.fleenmobile.vocabularymaster.data.model.StatKey;
import com.fleenmobile.vocabularymaster.data.model.Stats;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;
import com.fleenmobile.vocabularymaster.utils.RxSchedulersOverrideRule;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit Tests for {@link StatisticsPresenter}
 *
 * @author FleenMobile at 2016-10-27
 */
public class StatisticsPresenterTest {

    @Mock
    private Application mApplication;
    @Mock
    private StatisticsContract.View mView;
    @Mock
    private VocabularyDataSource mDataSource;

    private StatisticsPresenter mPresenter;
    private TestSubscriber<List<Vocabulary>> mVocabularyListTestSubscriber;
    private TestSubscriber<Vocabulary> mVocabularyTestSubscriber;

    // This rule prevents problems with not mocked schedulers
    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    private int amount = 10;
    private int offset = 0;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mVocabularyTestSubscriber = new TestSubscriber<>();
        mVocabularyListTestSubscriber = new TestSubscriber<>();

        mPresenter = new StatisticsPresenter(mDataSource, mView);
        mPresenter.setupListeners();

        // Make sure that view is 'active' (normally it would be active only after starting app)
        when(mView.isActive()).thenReturn(true);
        // Make sure repo returns sth
        when(mDataSource.getStats()).thenReturn(Observable.just(new Stats(new HashMap<StatKey, Long>())));
        when(mDataSource.getLearntVocabularySortedBy(any(Integer.class), any(Integer.class), any(VocabularySortingStrategy.class))).thenReturn(Observable.just(Lists.newArrayList()));
    }

    @Test
    public void subscribe_allProgressBarsAreShown_atTheBeginning() {
        // ================ GIVEN ================

        // ================ WHEN ================
        mPresenter.subscribe();

        // ================ THEN ================
        verify(mView).onLoadingMainStats();
        verify(mView).onLoadingTopKnownVocabulary();
        verify(mView).onLoadingWorstKnownVocabulary();
    }

    @Test
    public void loadMainStats_loadsCorrectStats_whenSomethingInDB() {
        // ================ GIVEN ================
        // We have sth in DB
        mDataSource.fillOutDB();

        // ================ WHEN ================
        // Loading main stats
        mPresenter.loadMainStats();

        // ================ THEN ================
        // Loads stats from data source
        verify(mDataSource).getStats();
        // Informs view about it
        verify(mView).onLoadedMainStats(any(Stats.class));
    }

    @Test
    public void loadTopKnownVocabulary_loadsList() {
        // ================ GIVEN ================
        // We have sth in DB
        mDataSource.fillOutDB();

        // ================ WHEN ================
        // Loading top known vocabulary
        mPresenter.loadTopKnownVocabulary(amount, offset);

        // ================ THEN ================
        // Loads vocabulary from data source
        verify(mDataSource).getLearntVocabularySortedBy(eq(amount), eq(offset), eq(VocabularySortingStrategy.SORT_BY_CORRECT_TRIES_PERC_DESC));
        // Informs view about it
        verify(mView).onLoadedTopKnownVocabulary(anyListOf(Vocabulary.class));
    }

    @Test
    public void loadWorstKnownVocabulary_loadsList() {
        // ================ GIVEN ================
        // We have sth in DB
        mDataSource.fillOutDB();

        // ================ WHEN ================
        // Loading worst known vocabulary
        mPresenter.loadWorstKnownVocabulary(amount, offset);

        // ================ THEN ================
        // Loads vocabulary from data source
        verify(mDataSource).getLearntVocabularySortedBy(eq(amount), eq(offset), eq(VocabularySortingStrategy.SORT_BY_CORRECT_TRIES_PERC));
        // Informs view about it
        verify(mView).onLoadedWorstKnownVocabulary(anyListOf(Vocabulary.class));
    }

}