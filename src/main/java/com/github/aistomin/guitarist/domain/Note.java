package com.github.aistomin.guitarist.domain;

/**
 * Created by aistomin on 08.10.18.
 * <p>
 * All the notes available on the 6-strings guitar in the standard
 * tuning (E-A-D-G-B-E). 1-12 frets. The notes are named according to Helmholtz
 * pitch notation.
 */
public enum Note {
    E,
    F,
    Fsharp,
    G,
    Gsharp,
    A,
    Asharp,
    B,
    c,
    csharp,
    d,
    dsharp,
    e,
    f,
    fsharp,
    g,
    gsharp,
    a,
    asharp,
    b,
    c_,
    c_sharp,
    d_,
    d_sharp,
    e_,
    f_,
    f_sharp,
    g_,
    g_sharp,
    a_,
    a_sharp,
    b_,
    c__,
    c__sharp,
    d__,
    d__sharp,
    e__;

    /**
     * The name of the note in the Helmholtz pitch notation.
     *
     * @return The name.
     */
    public String helmholtzName() {
        return name().replace("_", "'").replace("sharp", "#");
    }
}
