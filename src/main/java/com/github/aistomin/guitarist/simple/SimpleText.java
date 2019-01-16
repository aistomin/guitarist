package com.github.aistomin.guitarist.simple;

import com.github.aistomin.guitarist.QuestionsText;
import java.util.HashMap;
import org.json.simple.JSONObject;

/**
 * Created by aistomin on 2019-01-16.
 * <p>
 * The simple implementation of the {@link QuestionsText}
 */
public class SimpleText implements QuestionsText {

    /**
     * The simple text.
     */
    private final String text;

    /**
     * Ctor.
     *
     * @param text The simple text.
     */
    public SimpleText(final String text) {
        this.text = text;
    }

    @Override
    public JSONObject toJson() {
        final HashMap<String, Object> json = new HashMap<>();
        json.put("text", this.text);
        return new JSONObject(json);
    }

    @Override
    public String toDisplayableString() {
        return this.text;
    }
}
