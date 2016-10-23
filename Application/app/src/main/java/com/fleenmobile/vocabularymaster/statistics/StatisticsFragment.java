package com.fleenmobile.vocabularymaster.statistics;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fleenmobile.vocabularymaster.R;
import com.fleenmobile.vocabularymaster.adding_words.AddFilePopupContract;
import com.fleenmobile.vocabularymaster.adding_words.AddFilePopupView;
import com.fleenmobile.vocabularymaster.adding_words.AddOneVocabularyPopupView;
import com.fleenmobile.vocabularymaster.adding_words.BuyVocabularyPopupView;
import com.fleenmobile.vocabularymaster.data.model.StatKey;
import com.fleenmobile.vocabularymaster.data.model.Stats;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

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

    private static final int LOAD_MORE_COUNT = 6;
    private static boolean mActive = false;

    private StatisticsContract.Presenter mPresenter;
    private AddFilePopupContract.Presenter mAddFilePopupPresenter;

    @BindView(R.id.fab_menu_overlay)
    protected View fabMenuOverlay;
    @BindView(R.id.fab_add_vocabulary)
    protected FloatingActionMenu fabMenu;

    @BindView(R.id.stats_worst_known_recycler)
    protected RecyclerView worstKnownVocabularyFirstRecycler;
    @BindView(R.id.stats_top_known_recycler)
    protected RecyclerView topKnownVocabularyFirstRecycler;

    @BindView(R.id.stats_top_known_more)
    protected TextView topKnownMoreButton;
    @BindView(R.id.stats_worst_known_more)
    protected TextView worstKnownMoreButton;

    @BindView(R.id.stats_learnt_count)
    protected TextView learntCountTV;

    // Material sheet FABs
    @BindView(R.id.fab_add_one_vocabulary)
    protected FloatingActionButton addOneVocabularyFAB;
    private AddOneVocabularyPopupView addOneVocabularySheetView;

    @BindView(R.id.fab_add_file)
    protected FloatingActionButton addFileFAB;
    private AddFilePopupView addFileSheetView;

    @BindView(R.id.fab_buy_vocabulary)
    protected FloatingActionButton buyVocabularyFAB;
    private BuyVocabularyPopupView buyVocabularySheetView;

    private CorrectTriesPercAdapter mWorstKnownVocabularyAdapter;
    private CorrectTriesPercAdapter mTopKnownVocabularyAdapter;

    public static StatisticsFragment newInstance() {
        return new StatisticsFragment();
    }

    public void setPopups(AddFilePopupView mAddFileView, AddOneVocabularyPopupView mAddOneVocabularyView, BuyVocabularyPopupView mBuyVocabularyView) {
        addOneVocabularySheetView = mAddOneVocabularyView;
        addFileSheetView = mAddFileView;
        buyVocabularySheetView = mBuyVocabularyView;
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
        mPresenter.unsubscribe();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.f_statistics, container, false);
        ButterKnife.bind(this, root);

        fabMenu.setOnMenuButtonClickListener(mPresenter::onFabMenu);

        return root;
    }

    @Override
    public void onLoadingMainStats() {
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
        learntCountTV.setText(String.valueOf(stats.getmStats().get(StatKey.LEARNT_WORDS)));
    }

    @Override
    public void onLoadedTopKnownVocabulary(List<Vocabulary> vocabulary) {
        if (mWorstKnownVocabularyAdapter == null) {
            mTopKnownVocabularyAdapter = new CorrectTriesPercAdapter(getActivity(), vocabulary);
            topKnownVocabularyFirstRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            topKnownVocabularyFirstRecycler.setAdapter(mTopKnownVocabularyAdapter);
        } else {
            mTopKnownVocabularyAdapter.addItems(vocabulary);
            topKnownMoreButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoadedWorstKnownVocabulary(List<Vocabulary> vocabulary) {
        if (mWorstKnownVocabularyAdapter == null) {
            mWorstKnownVocabularyAdapter = new CorrectTriesPercAdapter(getActivity(), vocabulary);
            worstKnownVocabularyFirstRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
            worstKnownVocabularyFirstRecycler.setAdapter(mWorstKnownVocabularyAdapter);
        } else {
            mWorstKnownVocabularyAdapter.addItems(vocabulary);
            worstKnownMoreButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void expandFAB() {
        fabMenuOverlay.setVisibility(View.VISIBLE);
        fabMenu.open(true);
    }

    @Override
    public void collapseFAB() {
        fabMenu.close(true);
        fabMenuOverlay.setVisibility(View.GONE);
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

    @OnClick(R.id.fab_menu_overlay)
    public void onFabMenuOverlay(View v) {
        // Close FAB menu on click outside
        mPresenter.onFabMenu(v);
        dismissPopups();
    }

    private void dismissPopups() {
        if (addOneVocabularySheetView.isVisible()) addOneVocabularySheetView.dismiss();
        if (addFileSheetView.isVisible()) addFileSheetView.dismiss();
        if (buyVocabularySheetView.isVisible()) buyVocabularySheetView.dismiss();
    }

    @OnClick(R.id.stats_worst_known_more)
    public void onLoadMoreWorstKnown(View v) {
        mPresenter.loadWorstKnownVocabulary(LOAD_MORE_COUNT, mWorstKnownVocabularyAdapter.getItemCount());
    }

    @OnClick(R.id.stats_top_known_more)
    public void onLoadMoreTopKnown(View v) {
        mPresenter.loadTopKnownVocabulary(LOAD_MORE_COUNT, mTopKnownVocabularyAdapter.getItemCount());
    }

    @OnClick(R.id.fab_add_one_vocabulary)
    public void onAddOneVocabulary(View v) {
        if (addOneVocabularySheetView.isVisible())
            addOneVocabularySheetView.dismiss();
        else {
            dismissPopups();
            addOneVocabularySheetView.show(getActivity().getFragmentManager(), AddOneVocabularyPopupView.TAG);
        }
    }

    @OnClick(R.id.fab_add_file)
    public void onAddFile(View v) {
        if (addFileSheetView.isVisible())
            addFileSheetView.dismiss();
        else {
            dismissPopups();
            addFileSheetView.show(getActivity().getFragmentManager(), AddOneVocabularyPopupView.TAG);
        }
    }

    @OnClick(R.id.fab_buy_vocabulary)
    public void onBuyVocabulary(View v) {
        if (buyVocabularySheetView.isVisible())
            buyVocabularySheetView.dismiss();
        else {
            dismissPopups();
            buyVocabularySheetView.show(getActivity().getFragmentManager(), AddOneVocabularyPopupView.TAG);
        }
    }
}
