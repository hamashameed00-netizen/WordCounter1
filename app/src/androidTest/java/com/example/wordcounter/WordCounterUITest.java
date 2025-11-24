package com.example.wordcounter;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class WordCounterUITest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testWordCountButton_normalInput() {
        onView(withId(R.id.editTextInput))
                .perform(clearText(), typeText("Hello world"));

        onView(withId(R.id.buttonCount))
                .perform(click());

        onView(withId(R.id.textViewResult))
                .check(matches(withText("Word count: 2")));
    }

    @Test
    public void testWordCountButton_emptyInput() {
        onView(withId(R.id.editTextInput))
                .perform(clearText());

        onView(withId(R.id.buttonCount))
                .perform(click());

        onView(withId(R.id.textViewResult))
                .check(matches(withText("Word count: 0")));
    }
}
