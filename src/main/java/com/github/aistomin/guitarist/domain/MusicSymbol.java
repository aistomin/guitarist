package com.github.aistomin.guitarist.domain;

import com.github.aistomin.guitarist.Displayable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONObject;

/**
 * Created by aistomin on 04.10.18.
 * <p>
 * The enum contains symbols which we use to describe the music on the paper.
 */
public enum MusicSymbol implements Displayable {

    /**
     * Musical quarter note.
     */
    QUARTER_NOTE("♩", "Quarter Note", Type.NOTE_DURATION_SIGNS),

    /**
     * Musical eighth note.
     */
    EIGHTH_NOTE("♪", "Eighth Note", Type.NOTE_DURATION_SIGNS),

    /**
     * Musical single bar note.
     */
    SINGLE_BAR_NOTE("♫", "Single Bar Note", Type.NOTE_DURATION_SIGNS),

    /**
     * Musical double bar note
     */
    DOUBLE_BAR_NOTE("♬", "Double Bar Note", Type.NOTE_DURATION_SIGNS),

    /**
     * G clef.
     */
    CLEF_G("\uD834\uDD1E", "G Clef", Type.CLEFS),

    /**
     * C clef.
     */
    CLEF_C("\uD834\uDD21", "C Clef", Type.CLEFS),

    /**
     * F clef.
     */
    CLEF_F("\uD834\uDD22", "F Clef", Type.CLEFS),

    /**
     * Natural note.
     */
    NATURAL_NOTE("♮", "Natural Note", Type.SHARPS_AND_FLATS),

    /**
     * Flat note.
     */
    FLAT_NOTE("♭", "Flat Note", Type.SHARPS_AND_FLATS),

    /**
     * Sharp note.
     */
    SHARP_NOTE("♯", "Sharp Note", Type.SHARPS_AND_FLATS),

    /**
     * Double flat note.
     */
    DOUBLE_FLAT_NOTE("\uD834\uDD2B", "Double Flat Note", Type.SHARPS_AND_FLATS),

    /**
     * Double sharp note.
     */
    DOUBLE_SHARP_NOTE(
        "\uD834\uDD2A", "Double Sharp Note", Type.SHARPS_AND_FLATS
    ),

    /**
     * Musical Symbol Left Repeat Sign.
     */
    LEFT_REPEAT("\uD834\uDD06", "Left Repeat Sign", Type.REPEATS),

    /**
     * Musical Symbol Right Repeat Sign.
     */
    RIGHT_REPEAT("\uD834\uDD07", "Right Repeat Sign", Type.REPEATS),

    /**
     * Musical Symbol Repeat Dots.
     */
    REPEAT_DOTS("\uD834\uDD08", "Repeat Dots", Type.REPEATS),

    /**
     * Musical Symbol Fermata.
     */
    FERMATA("\uD834\uDD10", "Fermata", Type.DYNAMIC_SIGNS),

    /**
     * Musical Symbol Fermata Below.
     */
    FERMATA_BELOW("\uD834\uDD11", "Fermata Below", Type.DYNAMIC_SIGNS),

    /**
     * Musical Symbol Breath Mark.
     */
    BREATH("\uD834\uDD12", "Breath Mark", Type.DYNAMIC_SIGNS),

    /**
     * Musical Symbol Crescendo.
     */
    CRESCENDO("\uD834\uDD92", "Crescendo", Type.DYNAMIC_SIGNS),

    /**
     * Musical Symbol Decrescendo.
     */
    DECRESCENDO("\uD834\uDD93", "Decrescendo", Type.DYNAMIC_SIGNS);

    /**
     * Music symbol.
     */
    private final String symbol;

    /**
     * Symbol description.
     */
    private final String description;

    /**
     * The type of the symbol.
     */
    private final Type type;

    /**
     * Ctor.
     *
     * @param symbol      Music symbol.
     * @param description Symbol description.
     */
    MusicSymbol(
        final String symbol, final String description, final Type type
    ) {
        this.symbol = symbol;
        this.description = description;
        this.type = type;
    }

    /**
     * Music symbol.
     *
     * @return Music symbol string.
     */
    public String symbol() {
        return symbol;
    }

    /**
     * Symbol description.
     *
     * @return Symbol description.
     */
    public String description() {
        return description;
    }

    @Override
    public String toJsonString() {
        final HashMap<String, String> json = new HashMap<>();
        json.put("name", name());
        json.put("symbol", symbol());
        json.put("description", description());
        return new JSONObject(json).toJSONString();
    }

    @Override
    public String toDisplayableString() {
        return String.format("%s: %s", description(), symbol());
    }

    /**
     * Select symbols similar to this one. The current symbol will be also
     * included in the list. This list is used as an options of the
     * corresponding test.
     *
     * @return List of the similar symbols.
     */
    public List<MusicSymbol> similar() {
        final ArrayList<MusicSymbol> symbols = new ArrayList<>();
        for (final MusicSymbol symbol : values()) {
            if (symbol.type().equals(type())) {
                symbols.add(symbol);
            }
        }
        return symbols;
    }

    /**
     * Returns random musical symbol.
     *
     * @return Musical symbol.
     */
    public static MusicSymbol random() {
        final List<MusicSymbol> values = Arrays.asList(MusicSymbol.values());
        Collections.shuffle(values);
        return values.get(0);
    }

    /**
     * Return the type of the music symbol. This method has package local
     * visibility because of the unit tests which need this method.
     *
     * @return The type.
     */
    Type type() {
        return this.type;
    }

    /**
     * All the signs can be divided into groups according to its' purposes. So,
     * every type in this enum represents one of the music symbols groups.
     */
    enum Type {
        NOTE_DURATION_SIGNS,
        CLEFS,
        SHARPS_AND_FLATS,
        REPEATS,
        DYNAMIC_SIGNS
    }
}
