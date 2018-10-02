package com.github.aistomin.guitarist;

/**
 * Created by aistomin on 02.10.18.
 * <p>
 * The interface of the test's result.
 */
public interface Result {

    /**
     * Convert result to the valid JSON string.
     *
     * @return JSON string.
     */
    String toJsonString();

    /**
     * Convert the result to something which can be displayed in console.
     *
     * @return The displayable string.
     */
    String toString();
}
