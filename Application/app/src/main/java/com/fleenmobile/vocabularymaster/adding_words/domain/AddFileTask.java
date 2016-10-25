package com.fleenmobile.vocabularymaster.adding_words.domain;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.adding_words.VocabularyFileParser;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.fleenmobile.vocabularymaster.data.source.VocabularyDataSource;

import java.io.IOException;
import java.util.List;

import rx.Observable;

/**
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

    public AddFileTask(VocabularyDataSource repository, Uri uri, Context context) {
        this.mRepository = repository;
        this.mContext = context;
        this.mPath = FileUtils.getPath(context, uri);
        this.mParser = new VocabularyFileParser(mPath, context);
    }

    public Observable<List<Vocabulary>> execute() throws IOException {
        List<Vocabulary> vocabularyInFile = mParser.getVocabulary().toBlocking().first();
        return mRepository.addVocabulary(vocabularyInFile);
    }
}
