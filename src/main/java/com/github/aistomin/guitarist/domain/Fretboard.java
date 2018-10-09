package com.github.aistomin.guitarist.domain;

/**
 * Created by aistomin on 08.10.18.
 * <p>
 * 12 frets of the 6-strings guitar in the standard tuning (E-A-D-G-B-E).
 */
public final class Fretboard {

    /**
     * Ge the note of the current position on the fretboard.
     *
     * @param string   The string.
     * @param position The position on the string(1-12)
     * @return The corresponding note.
     */
    Note note(final Note string, final Integer position) {
        return Note.E;
    }
}
