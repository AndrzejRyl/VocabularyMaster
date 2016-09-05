package com.fleenmobile.vocabularymaster.data.model;

import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * Representing one translation of vocabulary (one vocabulary can have
 * several translations).
 *
 * @author FleenMobile at 2016-09-05
 */
public class Translation {

    @NonNull
    private String mTranslation;
    private boolean mKnown;
    private int mCorrectTries;
    private int mIncorrectTries;

    public Translation(@NonNull String mTranslation, boolean mKnown, int mCorrectTries, int mIncorrectTries) {
        this.mTranslation = checkNotNull(mTranslation, "Translation is null");
        this.mKnown = mKnown;
        this.mCorrectTries = mCorrectTries;
        this.mIncorrectTries = mIncorrectTries;
    }

    @NonNull
    public String getTranslation() {
        return mTranslation;
    }

    public void setTranslation(@NonNull String mTranslation) {
        this.mTranslation = mTranslation;
    }

    public boolean isKnown() {
        return mKnown;
    }

    public void setKnown(boolean mKnown) {
        this.mKnown = mKnown;
    }

    public int getCorrectTries() {
        return mCorrectTries;
    }

    public void setCorrectTries(int mCorrectTries) {
        this.mCorrectTries = mCorrectTries;
    }

    public int getIncorrectTries() {
        return mIncorrectTries;
    }

    public void setIncorrectTries(int mIncorrectTries) {
        this.mIncorrectTries = mIncorrectTries;
    }
}
