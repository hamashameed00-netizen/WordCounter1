package com.example.wordcounter;

import static org.junit.Assert.*;

import org.junit.Test;

public class WordCounterUnitTest {

    @Test
    public void testWordCount_normalInput() {
        String input = "Hello world from Android";
        int expectedCount = 4;

        int actualCount = WordCounter.countWords(input);
        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void testWordCount_emptyInput() {
        String input = "";
        int expectedCount = 0;

        int actualCount = WordCounter.countWords(input);
        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void testWordCount_multipleSpaces() {
        String input = "Hello   world   Android";
        int expectedCount = 3;

        int actualCount = WordCounter.countWords(input);
        assertEquals(expectedCount, actualCount);
    }
}
