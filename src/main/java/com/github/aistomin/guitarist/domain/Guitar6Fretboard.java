package com.github.aistomin.guitarist.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aistomin on 08.10.18.
 * <p>
 * 12 frets of the 6-strings guitar in the standard tuning (E-A-D-G-B-E).
 */
public final class Guitar6Fretboard implements Fretboard {

    /**
     * The fretboard strings <-> frets mapping.
     */
    private final Map<Note, List<Note>> mapping;

    /**
     * Ctor.
     */
    public Guitar6Fretboard() {
        mapping = new HashMap<>();
        mapping.put(Note.e_, eStringFrets());
        mapping.put(Note.b, bStringFrets());
        mapping.put(Note.g, gStringFrets());
        mapping.put(Note.d, dStringFrets());
        mapping.put(Note.A, AStringFrets());
        mapping.put(Note.E, EStringFrets());
    }

    @Override
    public Note note(final Note string, final Integer position) {
        return mapping.get(string).get(position);
    }

    @Override
    public List<Note> frets(final Note string) {
        return mapping.get(string);
    }

    @Override
    public List<Note> strings() {
        final ArrayList<Note> strings = new ArrayList<>(mapping.keySet());
        strings.sort(
            (o1, o2) -> -o1.compareTo(o2)
        );
        return strings;
    }

    /**
     * The frets of the e' string.
     *
     * @return The frets.
     */
    private List<Note> eStringFrets() {
        final List<Note> eString = new ArrayList<>();
        eString.add(Note.e_);
        eString.add(Note.f_);
        eString.add(Note.f_sharp);
        eString.add(Note.g_);
        eString.add(Note.g_sharp);
        return eString;
    }

    /**
     * The frets of the b string.
     *
     * @return The frets.
     */
    private List<Note> bStringFrets() {
        final List<Note> bString = new ArrayList<>();
        bString.add(Note.b);
        bString.add(Note.c_);
        bString.add(Note.c_sharp);
        bString.add(Note.d_);
        bString.add(Note.d_sharp);
        return bString;
    }

    /**
     * The frets of the g string.
     *
     * @return The frets.
     */
    private List<Note> gStringFrets() {
        final List<Note> gString = new ArrayList<>();
        gString.add(Note.g);
        gString.add(Note.gsharp);
        gString.add(Note.a);
        gString.add(Note.a_sharp);
        gString.add(Note.b);
        return gString;
    }

    /**
     * The frets of the d string.
     *
     * @return The frets.
     */
    private List<Note> dStringFrets() {
        final List<Note> dString = new ArrayList<>();
        dString.add(Note.d);
        dString.add(Note.d_sharp);
        dString.add(Note.e);
        dString.add(Note.f);
        dString.add(Note.f_sharp);
        return dString;
    }

    /**
     * The frets of the A string.
     *
     * @return The frets.
     */
    private List<Note> AStringFrets() {
        final List<Note> AString = new ArrayList<>();
        AString.add(Note.A);
        AString.add(Note.Asharp);
        AString.add(Note.B);
        AString.add(Note.c);
        AString.add(Note.csharp);
        return AString;
    }

    /**
     * The frets of the F string.
     *
     * @return The frets.
     */
    private List<Note> EStringFrets() {
        final List<Note> EString = new ArrayList<>();
        EString.add(Note.E);
        EString.add(Note.F);
        EString.add(Note.Fsharp);
        EString.add(Note.G);
        EString.add(Note.Gsharp);
        return EString;
    }
}
