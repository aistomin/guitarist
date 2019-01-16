package com.github.aistomin.guitarist.simple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Created by aistomin on 2019-01-16.
 * <p>
 * The test for {@link SimpleText}
 */
class SimpleTextTest {

    /**
     * Check that we correctly convert the entity to the JSON.
     *
     */
    @Test
    void toJsonString() {
        final String text = "bla-bla text";
        assertEquals(text, new SimpleText(text).toJson().get("text"));
    }

    /**
     * Check that we correctly display the text.
     */
    @Test
    void toDisplayableString() {
        final String text = "some text";
        assertEquals(text, new SimpleText(text).toDisplayableString());
    }
}
