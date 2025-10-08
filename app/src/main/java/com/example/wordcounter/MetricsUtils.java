package com.example.wordcounter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetricsUtils {

    /** WORDS: uses regex to find sequences of letters (Unicode-aware). */
    public static int countWordsWithRegex(String text) {
        if (text == null || text.isEmpty()) return 0;
        Pattern pattern = Pattern.compile("\\b\\p{L}+\\b"); // sequences of letters
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) count++;
        return count;
    }

    /** NUMBERS: uses regex to find integers or decimal numbers. */
    public static int countNumbersWithRegex(String text) {
        if (text == null || text.isEmpty()) return 0;
        Pattern pattern = Pattern.compile("\\b\\d+(?:\\.\\d+)?\\b");
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) count++;
        return count;
    }

    /** SENTENCES: non-regex implementation. Counts '.', '!' and '?' as terminators.
     *  Consecutive terminators ('...') count as one end-of-sentence. */
    public static int countSentencesNonRegex(String text) {
        if (text == null || text.isEmpty()) return 0;
        int count = 0;
        boolean lastWasTerminator = false;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '.' || c == '!' || c == '?') {
                if (!lastWasTerminator) {
                    count++;
                    lastWasTerminator = true;
                }
            } else if (!Character.isWhitespace(c)) {
                // reset flag when we encounter a non-space non-terminator char
                lastWasTerminator = false;
            }
        }
        return count;
    }

    /** CHARS: non-regex implementation. Counts characters including spaces/newlines.
     *  (If you want to exclude spaces, change to count non-whitespace characters.) */
    public static int countCharactersNonRegex(String text) {
        if (text == null) return 0;
        return text.length();
    }
}
