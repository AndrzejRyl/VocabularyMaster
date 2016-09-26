package com.fleenmobile.vocabularymaster;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.fleenmobile.vocabularymaster.adding_words.AddFilePopupPresenter;
import com.fleenmobile.vocabularymaster.adding_words.AddFilePopupView;
import com.fleenmobile.vocabularymaster.adding_words.AddOneVocabularyPopupPresenter;
import com.fleenmobile.vocabularymaster.adding_words.AddOneVocabularyPopupView;
import com.fleenmobile.vocabularymaster.adding_words.di.AddingWordsModule;
import com.fleenmobile.vocabularymaster.application.VocabularyApplication;
import com.fleenmobile.vocabularymaster.data.di.DataComponent;
import com.fleenmobile.vocabularymaster.statistics.StatisticsFragment;
import com.fleenmobile.vocabularymaster.statistics.StatisticsPresenter;
import com.fleenmobile.vocabularymaster.statistics.di.DaggerStatisticsComponent;
import com.fleenmobile.vocabularymaster.statistics.di.StatisticsModule;
import com.fleenmobile.vocabularymaster.utils.ActivityUtils;
import com.fleenmobile.vocabularymaster.utils.GoogleAnalyticsHelper;
import com.fleenmobile.vocabularymaster.utils.LogWrapper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Inject
    AddOneVocabularyPopupPresenter mAddOneVocabularyPresenter;
    @Inject
    AddFilePopupPresenter mAddFilePresenter;
    @Inject
    StatisticsPresenter mStatisticsPresenter;

    @Inject
    LogWrapper mLogWrapper;
    @Inject
    GoogleAnalyticsHelper mAnalyticsHelper;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.title)
    TextView toolbarTitle;
    ActionBarDrawerToggle toggle;

    private AddFilePopupView mAddFileView;
    private AddOneVocabularyPopupView mAddOneVocabularyView;
    private StatisticsFragment statisticsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupToolbar();
        setupNavigation();
        setupFragment();

        // Create presenters
        DataComponent dataComponent = ((VocabularyApplication) getApplication()).getDataComponent();

        DaggerStatisticsComponent.builder()
                .statisticsModule(new StatisticsModule(statisticsView))
                .addingWordsModule(new AddingWordsModule(mAddFileView, mAddOneVocabularyView))
                .dataComponent(dataComponent)
                .build()
                .inject(this);

        checkNotNull(mAddOneVocabularyPresenter);
        checkNotNull(mAddFilePresenter);
        checkNotNull(mStatisticsPresenter);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mAnalyticsHelper.sendScreenEvent(TAG);
        mLogWrapper.logDebug(TAG, "onResume");

        // Call subscribe on presenters of views that do not
        // have onResume method (extendning RelativeLayout i.e.)
        mAddFileView.subscribe();
        mAddOneVocabularyView.subscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Call unsubscribe on presenters of views that do not
        // have onDestroy method (extendning RelativeLayout i.e.)
        if (mAddFileView != null) mAddFileView.unsubscribe();
        if (mAddOneVocabularyView != null) mAddOneVocabularyView.unsubscribe();
    }

    @OnClick({R.id.stats, R.id.learning, R.id.revision, R.id.remove_cards, R.id.tutorial, R.id.help})
    public void onHandleNavigationItem(View v) {
        switch (v.getId()) {
            case R.id.stats:
                mLogWrapper.logDebug(TAG, "Stats clicked");
                break;
            case R.id.learning:
                mLogWrapper.logDebug(TAG, "Learning clicked");
                break;
            case R.id.revision:
                mLogWrapper.logDebug(TAG, "Revision clicked");
                break;
            case R.id.remove_cards:
                mLogWrapper.logDebug(TAG, "Remove cards clicked");
                break;
            case R.id.tutorial:
                mLogWrapper.logDebug(TAG, "Tutorial clicked");
                break;
            case R.id.help:
                mLogWrapper.logDebug(TAG, "Help clicked");
                break;

            default:
                mLogWrapper.logDebug(TAG, "Navigation item not found");
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void setTitle(CharSequence value) {
        toolbarTitle.setText(value);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        setTitle(getResources().getString(R.string.app_name));
    }

    private void setupNavigation() {
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                setTitle(getResources().getString(R.string.app_name));
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setTitle(getResources().getString(R.string.menu));
            }
        };
        drawer.addDrawerListener(toggle);
    }

    private void setupFragment() {
        mAddFileView = AddFilePopupView.newInstance(this);
        mAddOneVocabularyView = AddOneVocabularyPopupView.newInstance(this, mStatisticsPresenter);

        statisticsView = (StatisticsFragment) getSupportFragmentManager().findFragmentByTag(StatisticsFragment.class.getName().toUpperCase());
        if (statisticsView == null)
            statisticsView = StatisticsFragment.newInstance();

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), statisticsView, R.id.contentFrame);
    }
}
