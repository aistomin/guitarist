package com.github.aistomin.guitarist.simple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

/**
 * Created by aistomin on 02.10.18.
 * <p>
 * The tests for {@link SimpleAnswer}
 */
final class SimpleAnswerTest {

    /**
     * Check that we correctly validate the answers.
     */
    @Test
    void validate() {
        final String correct = "Correct answer";
        final String wrong = "Wrong answer";
        assertTrue(new SimpleAnswer(correct).validate(new SimpleAnswer(correct)));
        assertFalse(new SimpleAnswer(wrong).validate(new SimpleAnswer(correct)));
        assertFalse(new SimpleAnswer(correct).validate(new SimpleAnswer(wrong)));
    }

    /**
     * Check that we correctly convert the answer to the JSON string.
     *
     * @throws ParseException On JSON parsing exception.
     */
    @Test
    void toJsonString() throws ParseException {
        final String expected = "Expected answer";
        assertEquals(
            expected,
            (
                (JSONObject) new JSONParser()
                    .parse(new SimpleAnswer(expected).toJsonString())
            ).get("text")
        );
    }

    /**
     * Check that we correctly display the answer.
     */
    @Test
    void toDisplayableString() {
        final String expected = "Some text";
        assertEquals(
            expected, new SimpleAnswer(expected).toDisplayableString()
        );
    }
}
