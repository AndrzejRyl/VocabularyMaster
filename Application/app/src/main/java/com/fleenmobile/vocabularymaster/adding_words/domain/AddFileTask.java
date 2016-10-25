package com.fleenmobile.vocabularymaster.adding_words.domain;

import android.content.Context;
import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.adding_words.VocabularyFileParser;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;

import java.util.List;

import rx.Observable;

/**
 *
 * This task adds vocabulary from the file to the database
 *
 * @author FleenMobile at 2016-09-07
 */
public class AddFileTask {
    @NonNull
    private VocabularyDataSource mRepository;
    private VocabularyFileParser mParser;
    private String mPath;
    private Context mContext;

    public AddFileTask(VocabularyDataSource repository, String filePath, Context context) {
        this.mRepository = repository;
        this.mContext = context;
        this.mPath = filePath;
        this.mParser = new VocabularyFileParser(filePath, context);
    }

    public Observable<List<Vocabulary>> execute() {
        List<Vocabulary> vocabularyInFile = mParser.getVocabulary().toBlocking().first();
        return mRepository.addVocabulary(vocabularyInFile);
    }
}
