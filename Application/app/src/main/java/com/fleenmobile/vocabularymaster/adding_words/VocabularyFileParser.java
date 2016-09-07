package com.fleenmobile.vocabularymaster.adding_words;

import com.fleenmobile.vocabularymaster.data.model.Vocabulary;

import java.io.File;
import java.util.List;

import rx.Observable;

/**
 *
 * This class parses file and returns a list of vocabulary that was there
 *
 * @author FleenMobile at 2016-09-07
 */
public class VocabularyFileParser {

    private String mPath;
    private File mFile;

    public VocabularyFileParser(String path) {
        mPath = path;
    }

    public Observable<List<Vocabulary>> getVocabulary() {
        // TODO
        return null;
    }
}
