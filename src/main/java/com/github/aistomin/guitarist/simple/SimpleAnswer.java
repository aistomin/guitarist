package com.github.aistomin.guitarist.simple;

import com.github.aistomin.guitarist.Answer;
import java.util.HashMap;
import org.json.simple.JSONObject;

/**
 * Created by aistomin on 02.10.18.
 * <p>
 * The simple implementation of {@link Answer}
 */
public final class SimpleAnswer implements Answer {

    /**
     * The answer's text.
     */
    private final String text;

    /**
     * Ctor.
     *
     * @param text The answer's text.
     */
    public SimpleAnswer(final String text) {
        this.text = text;
    }

    @Override
    public Boolean validate(final Answer answer) {
        return answer != null && text.equals(answer.toDisplayableString());
    }

    @Override
    public JSONObject toJson() {
        final HashMap<String, String> json = new HashMap<>();
        json.put("text", text);
        return new JSONObject(json);
    }

    @Override
    public String toDisplayableString() {
        return text;
    }
}
