package com.github.aistomin.guitarist.domain;

import java.util.List;

/**
 * Created by aistomin on 09.10.18.
 * <p>
 * String instrument's fretboard.
 */
public interface Fretboard {

    /**
     * The note of the current position on the fretboard.
     *
     * @param string   The string.
     * @param position The position on the string.
     * @return The corresponding note.
     */
    Note note(Note string, Integer position);

    /**
     * All the frets of the particular string.
     *
     * @param string The string.
     * @return The frets.
     */
    List<Note> frets(Note string);

    /**
     * All the strings 0 - highest string, MAX - the lowest string.
     *
     * @return The open strings' notes.
     */
    List<Note> strings();
}
