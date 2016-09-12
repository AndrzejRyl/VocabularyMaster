package com.fleenmobile.vocabularymaster.utils;

import android.content.Context;

import com.fleenmobile.vocabularymaster.BuildConfig;
import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;
import com.google.code.microlog4android.config.PropertyConfigurator;

/**
 * @author FleenMobile at 2016-09-12
 */
public class FileLogger {
    private static Logger logger = LoggerFactory.getLogger();
    private static boolean mConfigured = false;

    public static void init(Context context) {
        try {
            PropertyConfigurator.getConfigurator(context).configure();
            mConfigured = true;
        } catch (Exception e) {
            // Don't crash please...
        }
    }

    public static void log(String tag, String mssg) {
        try {
            if (BuildConfig.FLAVOR.equals("dev_test")) {
                if (!mConfigured)
                    throw new IllegalStateException("You have to initalize the logger first !!!");
                else
                    logger.debug(String.format("[%s] %s", tag, mssg));

            }
        } catch (Exception e) {
            // Don't crash please....
        }
    }
}
