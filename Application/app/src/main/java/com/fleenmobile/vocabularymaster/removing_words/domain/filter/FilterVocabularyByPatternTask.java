package com.fleenmobile.vocabularymaster.removing_words.domain.filter;

import android.support.annotation.NonNull;
import android.widget.EditText;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;

import java.util.List;

import rx.Observable;

/**
 *
 * This task filters vocabulary by pattern written in provided EditText.
 *
 * @author FleenMobile at 2016-09-09
 */
public class FilterVocabularyByPatternTask {
    @NonNull
    private VocabularyDataSource mRepository;

    @NonNull
    private List<Vocabulary> mVocabulary;
    @NonNull
    private EditText mSearchBar;

    public FilterVocabularyByPatternTask(List<Vocabulary> vocabulary, EditText searchBar) {
        mVocabulary = vocabulary;
        mSearchBar = searchBar;
    }

    public Observable<List<Vocabulary>> execute() {
        // TODO
        return null;
    }
}
