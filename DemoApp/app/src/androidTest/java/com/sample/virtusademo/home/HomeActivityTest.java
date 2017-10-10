package com.sample.virtusademo.home;


import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.sample.virtusademo.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void setup() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void verifylayout() throws Exception {
        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.header1),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        relativeLayout.check(matches(isDisplayed()));

    }

    @Test
    public void verifyButtonShow() throws Exception {
        //test2 button test
        ViewInteraction button = onView(
                allOf(withId(R.id.button2),
                        childAtPosition(
                                allOf(withId(R.id.header1),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

    }
    @Test
    public void showWeather() {

        SystemClock.sleep(1000);
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.input_city_id),
                        withParent(withId(R.id.header1)),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("dallas"), closeSoftKeyboard());
        SystemClock.sleep(1000);
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button2), withText("Show"),
                        withParent(withId(R.id.header1)),
                        isDisplayed()));
        appCompatButton.perform(click());

        SystemClock.sleep(1000);

        ViewInteraction textView = onView(
                allOf(withId(R.id.tv_city), withText("Dallas, US"),
                        childAtPosition(
                                allOf(withId(R.id.rl_weather_desc),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Dallas, US")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
