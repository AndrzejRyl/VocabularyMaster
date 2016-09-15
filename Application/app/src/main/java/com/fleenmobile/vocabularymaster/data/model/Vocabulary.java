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
    private String word;
    @NonNull
    private List<Translation> mTranslations;
    /**
        Sum of correct tries of all translations
     **/
    private int mTotalCorrectTries;
    /**
        Sum of incorrect tries of all translations
     **/
    private int mTotalIncorrectTries;

    public Vocabulary(long mID, @NonNull String word, @NonNull List<Translation> mTranslations, int mTotalCorrectTries, int mTotalIncorrectTries) {
        this.mID = mID;
        this.word = checkNotNull(word, "Word is null in vocabulary with id: " + mID);
        this.mTranslations = checkNotNull(mTranslations, "List of translations is null in vocabulary with id: " + mID);
        this.mTotalCorrectTries = mTotalCorrectTries;
        this.mTotalIncorrectTries = mTotalIncorrectTries;
    }

    public long getID() {
        return mID;
    }

    public void setID(long mID) {
        this.mID = mID;
    }

    @NonNull
    public String getWord() {
        return word;
    }

    public void setWord(@NonNull String word) {
        this.word = word;
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
        double correctTriesPerc = (this.getTotalCorrectTries() / (this.getTotalCorrectTries() + this.getTotalIncorrectTries())) * 100.0;
        double otherCorrectTriesPerc = (that.getTotalCorrectTries() / (that.getTotalCorrectTries() + that.getTotalIncorrectTries())) * 100.0;

        if (sortedBy == VocabularySortingStrategy.SORT_BY_CORRECT_TRIES_PERC_DESC) {
            correctTriesPerc *= -1.0;
            otherCorrectTriesPerc *= -1.0;
        }

        return Double.compare(correctTriesPerc, otherCorrectTriesPerc);
    }
}
