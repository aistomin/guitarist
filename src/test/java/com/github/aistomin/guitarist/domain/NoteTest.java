package com.github.aistomin.guitarist.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Created by aistomin on 09.10.18.
 * <p>
 * The tests for {@link Note}
 */
class NoteTest {

    /**
     * Check that we correctly display the names of the notes in Helmholtz pitch
     * notation.
     */
    @Test
    void helmholtzName() {
        assertEquals("E", Note.E.helmholtzName());
        assertEquals("F#", Note.Fsharp.helmholtzName());
        assertEquals("c", Note.c.helmholtzName());
        assertEquals("d#", Note.dsharp.helmholtzName());
        assertEquals("c'", Note.c_.helmholtzName());
        assertEquals("d'#", Note.d_sharp.helmholtzName());
        assertEquals("c''", Note.c__.helmholtzName());
        assertEquals("d''#", Note.d__sharp.helmholtzName());
    }
}
