package com.fleenmobile.vocabularymaster.statistics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fleenmobile.vocabularymaster.R;
import com.fleenmobile.vocabularymaster.adding_words.AddFilePopupContract;
import com.fleenmobile.vocabularymaster.adding_words.AddOneVocabularyPopupContract;
import com.fleenmobile.vocabularymaster.data.model.Stats;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
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

    private StatisticsContract.Presenter mPresenter;
    private boolean mAddOneVocabularyPopupShown = false;
    private boolean mAddFileVocabularyPopupShown = false;
    private boolean mBuyVocabularyPopupShown = false;
    private boolean mFABExpanded = false;
    private AddFilePopupContract.Presenter mAddFilePopupPresenter;

    @BindView(R.id.stats_learnt_layout)
    protected ExpandableRelativeLayout mainStatsLayout;
    @BindView(R.id.fab_menu_overlay)
    protected View fabMenuOverlay;
    @BindView(R.id.fab_add_vocabulary)
    protected FloatingActionMenu fabMenu;

    @Inject
    AddOneVocabularyPopupContract.View mAddOneVocabularyPopup;
    @Inject
    AddFilePopupContract.View mAddFilePopup;

    public static StatisticsFragment newInstance() {
        return new StatisticsFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
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
        // TODO
    }

    @Override
    public void onLoadedTopKnownVocabulary(List<Vocabulary> vocabulary) {
        // TODO
    }

    @Override
    public void onLoadedWorstKnownVocabulary(List<Vocabulary> vocabulary) {
        // TODO
    }

    @Override
    public void expandFAB() {
        // TODO
    }

    @Override
    public void collapseFAB() {
        // TODO
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
        // TODO
        return false;
    }

    @Override
    public void setPresenter(StatisticsContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    private void onFabMenu(View v) {
        if (mFABExpanded) {
            fabMenu.close(true);
            fabMenuOverlay.setVisibility(View.GONE);
        } else {
            fabMenuOverlay.setVisibility(View.VISIBLE);
            fabMenu.open(true);
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
    }
}
