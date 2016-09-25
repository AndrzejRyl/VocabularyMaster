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
import com.fleenmobile.vocabularymaster.adding_words.AddOneVocabularyPopupContract;
import com.fleenmobile.vocabularymaster.application.VocabularyApplication;
import com.fleenmobile.vocabularymaster.data.model.StatKey;
import com.fleenmobile.vocabularymaster.data.model.Stats;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.view.RobotoTextView;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.github.clans.fab.FloatingActionMenu;

import java.util.List;

import javax.inject.Inject;

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

    private static boolean mActive = false;

    private StatisticsContract.Presenter mPresenter;
    private AddFilePopupContract.Presenter mAddFilePopupPresenter;

    private boolean mAddOneVocabularyPopupShown = false;
    private boolean mAddFileVocabularyPopupShown = false;
    private boolean mBuyVocabularyPopupShown = false;

    private boolean mFABExpanded = false;

    @BindView(R.id.stats_learnt_layout)
    protected ExpandableRelativeLayout mainStatsLayout;
    private boolean learntVocabularyLoaded = false;

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

    @BindView(R.id.stats_learnt_count)
    protected RobotoTextView learntCount;

    @Inject
    AddOneVocabularyPopupContract.View mAddOneVocabularyPopup;
    @Inject
    AddFilePopupContract.View mAddFilePopup;
    @Inject
    VocabularyApplication mApplication;

    private CorrectTriesPercAdapter worstKnownVocabularyAdapter;
    private CorrectTriesPercAdapter topKnownVocabularyAdapter;

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
        // TODO
    }

    @Override
    public void onLoadedLearntVocabulary(List<Vocabulary> vocabulary) {
        VocabularyTranslationAdapter learntVocabularyAdapter = new VocabularyTranslationAdapter(vocabulary);
        learntVocabularyRecycler.setLayoutManager(new LinearLayoutManager(mApplication));
        learntVocabularyRecycler.setAdapter(learntVocabularyAdapter);
    }

    @Override
    public void onLoadedTopKnownVocabulary(List<Vocabulary> vocabulary) {
        if (worstKnownVocabularyAdapter == null) {
            topKnownVocabularyAdapter = new CorrectTriesPercAdapter(vocabulary);
            topKnownVocabularyFirstRecycler.setLayoutManager(new LinearLayoutManager(mApplication));
            topKnownVocabularyFirstRecycler.setAdapter(topKnownVocabularyAdapter);
        }
        else
            worstKnownVocabularyAdapter.addItems(vocabulary);
    }

    @Override
    public void onLoadedWorstKnownVocabulary(List<Vocabulary> vocabulary) {
        if (worstKnownVocabularyAdapter == null) {
            worstKnownVocabularyAdapter = new CorrectTriesPercAdapter(vocabulary);
            worstKnownVocabularyFirstRecycler.setLayoutManager(new LinearLayoutManager(mApplication));
            worstKnownVocabularyFirstRecycler.setAdapter(worstKnownVocabularyAdapter);
        } else
            worstKnownVocabularyAdapter.addItems(vocabulary);
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
        mainStatsLayout.toggle();
        if (!learntVocabularyLoaded) {
            // TODO Load vocabulary in presenter
        }
    }

    @OnClick(R.id.stats_worst_known_more)
    public void onLoadMoreWorstKnown(View v) {
        // TODO Load vocabulary in presenter
    }

    @OnClick(R.id.stats_top_known_more)
    public void onLoadMoreTopKnown(View v) {
        // TODO Load vocabulary in presenter
    }
}
