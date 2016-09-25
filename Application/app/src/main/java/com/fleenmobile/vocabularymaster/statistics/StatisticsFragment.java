package com.fleenmobile.vocabularymaster.statistics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fleenmobile.vocabularymaster.R;
import com.fleenmobile.vocabularymaster.adding_words.AddFilePopupContract;
import com.fleenmobile.vocabularymaster.data.model.StatKey;
import com.fleenmobile.vocabularymaster.data.model.Stats;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.view.RobotoTextView;
import com.github.clans.fab.FloatingActionMenu;
import com.google.common.collect.Lists;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This fragment contains three main parts
 * - statistics about all learnt vocabulary (a list with translations)
 * - statistics about top known vocabulary (a list with percentage of correct tries sorted by it desc)
 * - statistics about worst known vocabulary (a list with percentage of correct tries sorted by it)
 *
 * @author FleenMobile at 2016-09-07
 */
public class StatisticsFragment extends Fragment implements StatisticsContract.View {

    private static final int LOAD_MORE_COUNT = 10;
    private static boolean mActive = false;

    private StatisticsContract.Presenter mPresenter;
    private AddFilePopupContract.Presenter mAddFilePopupPresenter;

    private boolean mAddOneVocabularyPopupShown = false;
    private boolean mAddFileVocabularyPopupShown = false;
    private boolean mBuyVocabularyPopupShown = false;

    private boolean mFABExpanded = false;

    private long mLearntVocabularyCount = -1L;

    private List<Vocabulary> mLearntVocabulary;

    @BindView(R.id.fab_menu_overlay)
    protected View fabMenuOverlay;
    @BindView(R.id.fab_add_vocabulary)
    protected FloatingActionMenu fabMenu;

    @BindView(R.id.stats_learnt_recycler)
    protected RecyclerView learntVocabularyRecycler;
    @BindView(R.id.stats_worst_known_recycler)
    protected RecyclerView worstKnownVocabularyFirstRecycler;
    @BindView(R.id.stats_top_known_recycler)
    protected RecyclerView topKnownVocabularyFirstRecycler;

    @BindView(R.id.stats_top_known_more)
    protected RobotoTextView topKnownMoreButton;
    @BindView(R.id.stats_worst_known_more)
    protected RobotoTextView worstKnownMoreButton;

    @BindView(R.id.stats_learnt_count)
    protected RobotoTextView learntCountTV;

    private CorrectTriesPercAdapter mWorstKnownVocabularyAdapter;
    private CorrectTriesPercAdapter mTopKnownVocabularyAdapter;
    private VocabularyTranslationAdapter mLearntVocabularyAdapter;

    public static StatisticsFragment newInstance() {
        return new StatisticsFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
        mActive = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        mActive = false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.f_statistics, container, false);
        ButterKnife.bind(this, root);
        fabMenu.setOnMenuButtonClickListener(this::onFabMenu);
        return root;
    }

    @Override
    public void onLoadingMainStats() {
        // TODO
    }

    @Override
    public void onLoadingLearntVocabulary() {
        // TODO
    }

    @Override
    public void onLoadingTopKnownVocabulary() {
        // TODO
    }

    @Override
    public void onLoadingWorstKnownVocabulary() {
        // TODO
    }

    @Override
    public void onLoadedMainStats(Stats stats) {
        mLearntVocabularyCount = stats.getmStats().get(StatKey.LEARNT_WORDS);
        learntCountTV.setText(String.valueOf(mLearntVocabularyCount));
    }

    @Override
    public void onLoadedLearntVocabulary(List<Vocabulary> vocabulary) {
        mLearntVocabularyAdapter = new VocabularyTranslationAdapter(getActivity(), vocabulary);
        learntVocabularyRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        learntVocabularyRecycler.setAdapter(mLearntVocabularyAdapter);
        learntVocabularyRecycler.setVisibility(View.VISIBLE);
        mLearntVocabulary = vocabulary;
    }

    @Override
    public void onLoadedTopKnownVocabulary(List<Vocabulary> vocabulary) {
        if (mWorstKnownVocabularyAdapter == null) {
            mTopKnownVocabularyAdapter = new CorrectTriesPercAdapter(getActivity(), vocabulary);
            topKnownVocabularyFirstRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            topKnownVocabularyFirstRecycler.setAdapter(mTopKnownVocabularyAdapter);
        } else
            mTopKnownVocabularyAdapter.addItems(vocabulary);

        if (vocabulary.size() == 0)
            topKnownMoreButton.setVisibility(View.GONE);
    }

    @Override
    public void onLoadedWorstKnownVocabulary(List<Vocabulary> vocabulary) {
        if (mWorstKnownVocabularyAdapter == null) {
            mWorstKnownVocabularyAdapter = new CorrectTriesPercAdapter(getActivity(), vocabulary);
            worstKnownVocabularyFirstRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            worstKnownVocabularyFirstRecycler.setAdapter(mWorstKnownVocabularyAdapter);
        } else
            mWorstKnownVocabularyAdapter.addItems(vocabulary);

        if (vocabulary.size() == 0)
            worstKnownMoreButton.setVisibility(View.GONE);
    }

    @Override
    public void expandFAB() {
        fabMenu.open(true);
    }

    @Override
    public void collapseFAB() {
        fabMenu.close(true);
    }

    @Override
    public void showAddOneVocabularyPopup() {
        // TODO
    }

    @Override
    public void showAddFilePopup() {
        // TODO
    }

    @Override
    public void showBuyVocabularyPopup() {
        // TODO
    }

    @Override
    public void onFileChosen(String filePath) {
        // TODO
    }

    @Override
    public boolean isActive() {
        return mActive;
    }

    @Override
    public void setPresenter(StatisticsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void onFabMenu(View v) {
        if (mFABExpanded) {
            collapseFAB();
            fabMenuOverlay.setVisibility(View.GONE);
        } else {
            fabMenuOverlay.setVisibility(View.VISIBLE);
            expandFAB();
        }
        mFABExpanded = !mFABExpanded;
    }

    @OnClick(R.id.fab_add_one_vocabulary)
    public void onAddOneVocabulary(View v) {
        showAddOneVocabularyPopup();
    }

    @OnClick(R.id.fab_add_file)
    public void onAddFile(View v) {
        showAddFilePopup();
    }

    @OnClick(R.id.fab_buy_vocabulary)
    public void onBuyVocabulary(View v) {
        showBuyVocabularyPopup();
    }

    @OnClick(R.id.stats_learnt_header)
    public void onMainStatsHeader(View v) {
        if (mLearntVocabulary == null && mLearntVocabularyCount != -1) {
            mPresenter.loadLearntVocabulary((int) mLearntVocabularyCount, 0);
        } else {
            if (mLearntVocabularyAdapter.getItemCount() == 0 && mLearntVocabulary != null) {
                learntVocabularyRecycler.setVisibility(View.VISIBLE);
                mLearntVocabularyAdapter.setItems(mLearntVocabulary);
            } else {
                learntVocabularyRecycler.setVisibility(View.GONE);
                mLearntVocabularyAdapter.setItems(Lists.newArrayList());
            }
        }
    }

    @OnClick(R.id.stats_worst_known_more)
    public void onLoadMoreWorstKnown(View v) {
        mPresenter.loadWorstKnownVocabulary(LOAD_MORE_COUNT, mWorstKnownVocabularyAdapter.getItemCount());
    }

    @OnClick(R.id.stats_top_known_more)
    public void onLoadMoreTopKnown(View v) {
        mPresenter.loadTopKnownVocabulary(LOAD_MORE_COUNT, mTopKnownVocabularyAdapter.getItemCount());
    }
}
