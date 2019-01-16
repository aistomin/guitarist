package com.github.aistomin.guitarist;

import org.json.simple.JSONObject;

/**
 * Created by aistomin on 02.10.18.
 */
public interface Displayable {

    /**
     * Convert the entity to something which can be displayed in console.
     *
     * @return The displayable string.
     */
    String toDisplayableString();

    /**
     * Convert the entity to the valid JSON.
     *
     * @return JSON.
     */
    JSONObject toJson();
}
