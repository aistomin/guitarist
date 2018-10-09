package com.github.aistomin.guitarist.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
     * Check common fretboard metrics:
     * 1. We correctly defined("tuned") the strings.
     * 2. The string note is the note of the 0 fret(open string).
     * 3. We have 12 frets + open string defined for each string.
     * 4. 12th fret is the same note as open string, but from octave higher.
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
        for (final Note string : strings) {
            final List<Note> frets = fretboard.frets(string);
            assertEquals(string, frets.get(0));
            assertEquals(1 + 12, frets.size());
            assertEquals(
                string.helmholtzName().toLowerCase().replace("'", ""),
                fretboard.note(string, 12).helmholtzName().toLowerCase()
                    .replace("'", "")
            );
        }
    }

    /**
     * Check that class properly reacts when his client is a total idiot.
     */
    @Test
    void dummyTest() {
        assertThrows(
            IllegalArgumentException.class, () -> fretboard.frets(Note.F)
        );
        assertThrows(
            IllegalArgumentException.class, () -> fretboard.note(Note.E, -1)
        );
        assertThrows(
            IllegalArgumentException.class, () -> fretboard.note(Note.g, 13)
        );
    }
}
