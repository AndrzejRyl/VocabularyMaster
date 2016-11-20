package com.fleenmobile.vocabularymaster.statistics;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.fleenmobile.vocabularymaster.MainActivity;
import com.fleenmobile.vocabularymaster.R;
import com.github.clans.fab.FloatingActionMenu;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

/**
 * Tests for main screen of the app containing statistics of learning progress
 *
 * @author FleenMobile at 2016-11-20
 */

@RunWith(AndroidJUnit4.class)
public class StatisticsScreenTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);
    private View overlay;
    private FloatingActionMenu menu;

    @Test
    public void clickMoreButtonInTopKnownVocabularySection_hidesButton() {
        // ================ GIVEN ================

        // ================ WHEN ================
        onView(withId(R.id.stats_top_known_more)).perform(scrollTo()).perform(click());

        // ================ THEN ================
        onView(withId(R.id.stats_top_known_more)).check(matches(not(isDisplayed())));
    }

    @Test
    public void clickMoreButtonInWorstKnownVocabularySection_hidesButton() {
        // ================ GIVEN ================

        // ================ WHEN ================
        onView(withId(R.id.stats_worst_known_more)).perform(scrollTo()).perform(click());

        // ================ THEN ================
        onView(withId(R.id.stats_worst_known_more)).check(matches(not(isDisplayed())));
    }

    @Test
    public void clickFAB_expandsIt() {
        // ================ GIVEN ================

        // ================ WHEN ================
        openFABMenu();

        // ================ THEN ================
        onView(withId(R.id.fab_add_one_vocabulary)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_add_file)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_buy_vocabulary)).check(matches(isDisplayed()));
    }

    @Test
    public void clickMenuOverlay_hidesFABs() throws InterruptedException {
        // ================ GIVEN ================

        // ================ WHEN ================
        openFABMenu();
        Thread.sleep(1000);
        closeFABMenu();

        // ================ THEN ================
        onView(withId(R.id.fab_add_one_vocabulary)).check(matches(not(isDisplayed())));
        onView(withId(R.id.fab_add_file)).check(matches(not(isDisplayed())));
        onView(withId(R.id.fab_buy_vocabulary)).check(matches(not(isDisplayed())));
    }

    @Test
    public void clickAddOneVocabularyFAB_showsPopup() {
        // ================ GIVEN ================

        // ================ WHEN ================
        openFABMenu();
        onView(withId(R.id.fab_add_one_vocabulary)).perform(click());

        // ================ THEN ================
        onView(withId(R.id.add_one_vocabulary_popup_header)).check(matches(isDisplayed()));
    }

    @Test
    public void clickMenuOverlay_hidesPopup_whenAddOneVocabularyPopupVisible() {
        // ================ GIVEN ================

        // ================ WHEN ================
        openFABMenu();
        onView(withId(R.id.fab_add_one_vocabulary)).perform(click());
        mActivityTestRule.getActivity().runOnUiThread(overlay::callOnClick);

        // ================ THEN ================
        onView(withId(R.id.add_one_vocabulary_popup_header)).check(doesNotExist());
    }

    @Test
    public void clickAddFileFAB_showsPopup() {
        // ================ GIVEN ================

        // ================ WHEN ================
        openFABMenu();
        onView(withId(R.id.fab_add_file)).perform(click());

        // ================ THEN ================
        onView(withId(R.id.add_file_popup_header)).check(matches(isDisplayed()));
    }

    @Test
    public void clickMenuOverlay_hidesPopup_whenAddFilePopupVisible() {
        // ================ GIVEN ================

        // ================ WHEN ================
        openFABMenu();
        onView(withId(R.id.fab_add_file)).perform(click());
        mActivityTestRule.getActivity().runOnUiThread(overlay::callOnClick);

        // ================ THEN ================
        onView(withId(R.id.add_file_popup_header)).check(doesNotExist());
    }

    @Test
    public void clickBuyVocabularyFAB_showsPopup() {
        // ================ GIVEN ================

        // ================ WHEN ================
        openFABMenu();
        onView(withId(R.id.fab_buy_vocabulary)).perform(click());

        // ================ THEN ================
        // TODO: Uncomment it after merging buy_vocabulary popup
//        onView(withId(R.id.buy_vocabulary_header)).check(matches(isDisplayed()));
    }

    @Test
    public void clickMenuOverlay_hidesPopup_whenBuyVocabularyPopupVisible() {
        // ================ GIVEN ================

        // ================ WHEN ================
        openFABMenu();
        onView(withId(R.id.fab_buy_vocabulary)).perform(click());
        mActivityTestRule.getActivity().runOnUiThread(overlay::callOnClick);

        // ================ THEN ================
        // TODO: Uncomment it after merging buy_vocabulary popup
//        onView(withId(R.id.buy_vocabulary_header)).check(doesNotExist());
    }

    private void openFABMenu() {
        // I couldn't open menu just by clicking on FAB. Library that provided this menu handles it in a different way.
        // It creates a new FAB (without any specific ID) which captures all onClick events ;)
        menu = (FloatingActionMenu) mActivityTestRule.getActivity().findViewById(R.id.fab_add_vocabulary);
        menu.post(() -> {
            overlay = mActivityTestRule.getActivity().findViewById(R.id.fab_menu_overlay);
            menu.open(false);
        });
    }

    private void closeFABMenu() {
        menu.post(() -> {
            menu.close(false);
        });
    }

    @Test
    public void openScreen_showsLoadingProgressBars_untilContentLoaded() {
        // For now I decided not to use progress bars as data loads really quickly
        // and they won't be even visible. I will get back to it when I decide to load
        // data from a remote data source
    }
}
