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
        final ArrayList<Note> eString = new ArrayList<>();
        eString.add(Note.e_);
        mapping.put(Note.e_, eString);
        final ArrayList<Note> bString = new ArrayList<>();
        bString.add(Note.b);
        mapping.put(Note.b, bString);
        final ArrayList<Note> gString = new ArrayList<>();
        gString.add(Note.g);
        mapping.put(Note.g, gString);
        final ArrayList<Note> dString = new ArrayList<>();
        dString.add(Note.d);
        mapping.put(Note.d, dString);
        final ArrayList<Note> AString = new ArrayList<>();
        AString.add(Note.A);
        mapping.put(Note.A, AString);
        final ArrayList<Note> EString = new ArrayList<>();
        EString.add(Note.E);
        mapping.put(Note.E, EString);
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
}
