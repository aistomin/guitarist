package com.github.aistomin.guitarist.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Created by aistomin on 09.10.18.
 * <p>
 * The test for {@link Guitar6Fretboard}
 */
class Guitar6FretboardTest {

    /**
     * Fretboard instance.
     */
    private final Fretboard fretboard = new Guitar6Fretboard();

    /**
     * Check that we correctly "tuned" the open strings.
     */
    @Test
    void openStrings() {
        assertEquals(Note.e_, fretboard.note(Note.e_, 0));
        assertEquals(Note.b, fretboard.note(Note.b, 0));
        assertEquals(Note.g, fretboard.note(Note.g, 0));
        assertEquals(Note.d, fretboard.note(Note.d, 0));
        assertEquals(Note.A, fretboard.note(Note.A, 0));
        assertEquals(Note.E, fretboard.note(Note.E, 0));
    }

    /**
     * Check common fretboard metrics.
     */
    @Test
    void commonMetrics() {
        final List<Note> strings = fretboard.strings();
        assertEquals(Note.e_, strings.get(0));
        assertEquals(Note.b, strings.get(1));
        assertEquals(Note.g, strings.get(2));
        assertEquals(Note.d, strings.get(3));
        assertEquals(Note.A, strings.get(4));
        assertEquals(Note.E, strings.get(5));
        final List<Note> frets = fretboard.frets(strings.get(0));
        for (final Note string : strings) {
            assertEquals(frets.size(), fretboard.frets(string).size());
        }
    }
}
