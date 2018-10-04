package com.github.aistomin.guitarist.domain;

import com.github.aistomin.guitarist.Displayable;
import java.util.HashMap;
import org.json.simple.JSONObject;

/**
 * Created by aistomin on 04.10.18.
 * <p>
 * The enum contains symbols which we use to describe the music on the paper.
 */
public enum MusicSymbols implements Displayable {

    /**
     * Musical quarter note.
     */
    QUARTER_NOTE("♩"),

    /**
     * Musical eighth note.
     */
    EIGHTH_NOTE("♪"),

    /**
     * Musical single bar note.
     */
    SINGLE_BAR_NOTE("♫"),

    /**
     * Musical double bar note
     */
    DOUBLE_BAR_NOTE("♬"),

    /**
     * G clef.
     */
    CLEF_G("\uD834\uDD1E"),

    /**
     * C clef.
     */
    CLEF_C("\uD834\uDD21"),

    /**
     * F clef.
     */
    CLEF_F("\uD834\uDD22");

    /**
     * Music symbol.
     */
    private String symbol;

    /**
     * Ctor.
     *
     * @param symbol Music symbol.
     */
    MusicSymbols(final String symbol) {
        this.symbol = symbol;
    }

    /**
     * Music symbol.
     *
     * @return Music symbol string.
     */
    public String symbol() {
        return symbol;
    }

    @Override
    public String toJsonString() {
        final HashMap<String, String> json = new HashMap<>();
        json.put("name", name());
        json.put("symbol", symbol());
        return new JSONObject(json).toJSONString();
    }

    @Override
    public String toDisplayableString() {
        return String.format("%s: %s", name(), symbol());
    }
}
