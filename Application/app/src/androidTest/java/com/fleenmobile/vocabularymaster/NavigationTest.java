package com.fleenmobile.vocabularymaster;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests navigation drawer
 */
@RunWith(AndroidJUnit4.class)
public class NavigationTest {
    
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);
    
    @Test
    public void clickOnStatistics_opensStatisticsFragment() {
        // ================ GIVEN ================
        
        // ================ WHEN ================
        
        // ================ THEN ================ 
    }

    // TODO
}