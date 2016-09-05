package com.fleenmobile.vocabularymaster.data.model;

import android.support.annotation.NonNull;

import java.util.Map;

/**
 * @author FleenMobile at 2016-09-06
 */
public class Stats {

    @NonNull
    private Map<StatKey, Long> mStats;

    public Stats(@NonNull Map<StatKey, Long> mStats) {
        this.mStats = mStats;
    }

    @NonNull
    public Map<StatKey, Long> getmStats() {
        return mStats;
    }

    public void setmStats(@NonNull Map<StatKey, Long> mStats) {
        this.mStats = mStats;
    }
}
