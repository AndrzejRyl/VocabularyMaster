package com.fleenmobile.vocabularymaster.data.model;

import android.support.annotation.NonNull;

import com.fleenmobile.vocabularymaster.data.VocabularySortingStrategy;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author FleenMobile at 2016-09-05
 */
public class Vocabulary {

    private long mID;
    @NonNull
    private String mWord;
    @NonNull
    private List<Translation> mTranslations;
    /**
     * Sum of correct tries of all translations
     **/
    private int mTotalCorrectTries;
    /**
     * Sum of incorrect tries of all translations
     **/
    private int mTotalIncorrectTries;

    public Vocabulary(long mID, @NonNull String word, @NonNull List<Translation> mTranslations, int mTotalCorrectTries, int mTotalIncorrectTries) {
        this.mID = mID;
        this.mWord = checkNotNull(word, "Word is null in vocabulary with id: " + mID);
        this.mTranslations = checkNotNull(mTranslations, "List of translations is null in vocabulary with id: " + mID);
        this.mTotalCorrectTries = mTotalCorrectTries;
        this.mTotalIncorrectTries = mTotalIncorrectTries;
    }

    /**
     * Simpler constructor for mechanisms adding vocabulary from user input. They only know
     * word and translation. Everything else is generated in data source
     */
    public Vocabulary(@NonNull String word, @NonNull List<Translation> translations) {
        this.mID = -1;
        this.mWord = word;
        this.mTranslations = translations;
    }

    public long getID() {
        return mID;
    }

    public void setID(long mID) {
        this.mID = mID;
    }

    @NonNull
    public String getWord() {
        return mWord;
    }

    public void setWord(@NonNull String word) {
        this.mWord = word;
    }

    @NonNull
    public List<Translation> getTranslations() {
        return mTranslations;
    }

    public void setTranslations(@NonNull List<Translation> mTranslations) {
        this.mTranslations = mTranslations;
    }

    public int getTotalCorrectTries() {
        return mTotalCorrectTries;
    }

    public void setTotalCorrectTries(int mTotalCorrectTries) {
        this.mTotalCorrectTries = mTotalCorrectTries;
    }

    public int getTotalIncorrectTries() {
        return mTotalIncorrectTries;
    }

    public void setTotalIncorrectTries(int mTotalIncorrectTries) {
        this.mTotalIncorrectTries = mTotalIncorrectTries;
    }

    public int compare(Vocabulary that, VocabularySortingStrategy sortedBy) {
        double correctTriesPerc = (this.getTotalCorrectTries() * 100.0) / (this.getTotalCorrectTries() + this.getTotalIncorrectTries());
        double otherCorrectTriesPerc = (that.getTotalCorrectTries() * 100.0) / (that.getTotalCorrectTries() + that.getTotalIncorrectTries());

        if (sortedBy == VocabularySortingStrategy.SORT_BY_CORRECT_TRIES_PERC_DESC)
            return Double.compare(otherCorrectTriesPerc, correctTriesPerc);
        else
            return Double.compare(correctTriesPerc, otherCorrectTriesPerc);
    }
}
