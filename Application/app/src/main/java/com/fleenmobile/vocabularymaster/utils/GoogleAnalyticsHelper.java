package com.fleenmobile.vocabularymaster.utils;

import android.app.Application;

import com.fleenmobile.vocabularymaster.BuildConfig;
import com.fleenmobile.vocabularymaster.R;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * @author FleenMobile at 2016-09-12
 */
public class GoogleAnalyticsHelper {
    private static Tracker mTracker = null;

    public GoogleAnalyticsHelper(Application application) {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(application);// Set the log level to verbose.
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
    }

    public void sendScreenEvent(String name) {
        if (mTracker == null || BuildConfig.DEBUG)
            return;
        mTracker.setScreenName(name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    public void sendEvent(GoogleAnalyticsEvent event) {
        if (mTracker == null || BuildConfig.DEBUG)
            return;
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory(event.getCategory())
                .setAction(event.getAction())
                .setLabel(event.getLabel())
                .setValue(event.getValue())
                .build());
    }
}