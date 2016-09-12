package com.fleenmobile.vocabularymaster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    AddOneVocabularyPopupPresenter mAddOneVocabularyPresenter;
    @Inject
    AddFilePopupPresenter mAddFilePresenter;
    @Inject
    StatisticsPresenter mStatisticsPresenter;

    private AddFilePopupView mAddFileView;
    private AddOneVocabularyPopupView mAddOneVocabularyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO : check for existing fragments
        mAddFileView = AddFilePopupView.newInstance(this);
        mAddOneVocabularyView = AddOneVocabularyPopupView.newInstance(this);
        StatisticsFragment statisticsView = StatisticsFragment.newInstance();

        // Create presenters
        DataComponent dataComponent = ((VocabularyApplication) getApplication()).getDataComponent();

        DaggerStatisticsComponent.builder()
                .statisticsModule(new StatisticsModule(statisticsView))
                .addingWordsModule(new AddingWordsModule(mAddFileView, mAddOneVocabularyView))
                .dataComponent(dataComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

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
}
