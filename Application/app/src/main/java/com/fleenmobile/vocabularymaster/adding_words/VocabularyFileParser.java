package com.fleenmobile.vocabularymaster.adding_words;

import android.content.Context;

import com.fleenmobile.vocabularymaster.data.model.Translation;
import com.fleenmobile.vocabularymaster.data.model.Vocabulary;
import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import rx.Observable;

/**
 * This class parses file and returns a list of vocabulary that was there
 *
 * @author FleenMobile at 2016-09-07
 */
public class VocabularyFileParser {

    private String mPath;
    private Context mContext;
    private File mFile;

    public VocabularyFileParser(String path, Context context) {
        mPath = path;
        mContext = context;
        if (mPath != null)
            mFile = new File(mPath);
    }

    public Observable<List<Vocabulary>> getVocabulary() throws IOException {
        List<Vocabulary> result = Lists.newArrayList();
        if (mFile != null) {
            BufferedReader reader = new BufferedReader(new FileReader(mFile));

            String line;
            String[] data;
            Translation translation;
            while (reader.ready()) {
                line = reader.readLine();
                data = line.split(",|;");
                translation = new Translation(data[1], false, 0, 0);
                result.add(new Vocabulary(data[0], Lists.newArrayList(translation)));
            }
        }

        return Observable.just(result);
    }
}
