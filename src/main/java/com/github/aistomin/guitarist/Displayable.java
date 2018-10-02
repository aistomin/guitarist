package com.github.aistomin.guitarist;

import org.json.simple.parser.ParseException;

/**
 * Created by aistomin on 02.10.18.
 */
public interface Displayable {

    /**
     * Convert the entity to the valid JSON string.
     *
     * @return JSON string.
     * @throws ParseException On JSON parsing exception.
     */
    String toJsonString() throws ParseException;

    /**
     * Convert the entity to something which can be displayed in console.
     *
     * @return The displayable string.
     */
    String toDisplayableString();
}
