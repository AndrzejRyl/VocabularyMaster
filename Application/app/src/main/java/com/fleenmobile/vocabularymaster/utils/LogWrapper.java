package com.fleenmobile.vocabularymaster.utils;


import android.app.Application;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;

import java.util.Date;

/**
 * @author FleenMobile at 2016-09-12
 */
public class LogWrapper {

    private static final Logger logger = LoggerFactory.getLogger();
    private static final String TAG = "[Vocabulary++]";

    public LogWrapper(Application context) {
        FileLogger.init(context);
    }

    public void logDebug(String message) {
        logDebug(TAG, message);
    }

    public void logDebug(String tag, String message) {
        // Add date to messgage
        message = String.format("[%s] %s", new Date().toString(), message);

        Log.d(tag, message);
        Crashlytics.log(Log.DEBUG, TAG + tag, message);
        // Add logging to file on 'dev_test' flavour
        FileLogger.log(tag, message);
    }

    public void logError(String message) {
        logError(TAG, message);
    }

    public void logError(String tag, String message) {
        // Add date to messgage
        message = String.format("[%s] %s", new Date().toString(), message);

        Log.e(tag, message);
        Crashlytics.log(Log.ERROR, TAG + tag, message);
        // Add logging to file on 'dev_test' flavour
        FileLogger.log("ERROR: " + tag, message);
    }
}
