package com.github.aistomin.guitarist.simple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

/**
 * Created by aistomin on 02.10.18.
 * <p>
 * The tests for {@link SimpleResult}
 */
final class SimpleResultTest {

    /**
     * Check that we can correctly create the simple test result if we provide
     * correct arguments.
     */
    @Test
    void testConstructor() {
        final String nulls = "All the constructor parameters must be provided.";
        assertEquals(
            nulls, assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleResult(null, 0, 0, 0)
            ).getMessage()
        );
        assertEquals(
            nulls,
            assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleResult(0, null, 0, 0)
            ).getMessage()
        );
        assertEquals(
            nulls, assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleResult(0, 0, null, 0)
            ).getMessage()
        );
        assertEquals(
            nulls, assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleResult(0, 0, 0, null)
            ).getMessage()
        );
        final String positives = "All the constructor parameters must be positive.";
        assertEquals(
            positives, assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleResult(-1, 0, 0, 0)
            ).getMessage()
        );
        assertEquals(
            positives, assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleResult(0, -1, 0, 0)
            ).getMessage()
        );
        assertEquals(
            positives, assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleResult(0, 0, -1, 0)
            ).getMessage()
        );
        assertEquals(
            positives, assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleResult(0, 0, 0, -1)
            ).getMessage()
        );
        final String common = "Constructor parameters must not contradict the common sense.";
        assertEquals(
            common, assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleResult(6, 7, 4, 3)
            ).getMessage()
        );
        assertEquals(
            common, assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleResult(7, 6, 4, 3)
            ).getMessage()
        );
        assertNotNull(new SimpleResult(7, 6, 4, 2).toDisplayableString());
    }

    /**
     * Check that we correctly define which test is finished and which is not.
     */
    @Test
    void isFinished() {
        assertFalse(new SimpleResult(7, 6, 4, 2).isFinished());
        assertTrue(new SimpleResult(7, 7, 5, 2).isFinished());
    }

    /**
     * Check that we correctly define which test is passed and which is not.
     */
    @Test
    void isPassed() {
        assertFalse(new SimpleResult(7, 6, 4, 2).isPassed());
        assertFalse(new SimpleResult(7, 7, 5, 2).isPassed());
        assertTrue(new SimpleResult(7, 7, 7, 0).isPassed());
    }

    /**
     * Check that we correctly convert the result to JSON string.
     *
     * @throws ParseException JSON parsing exception.
     */
    @Test
    void toJsonString() throws ParseException {
        final int total = 7;
        final int answered = 6;
        final int correct = 4;
        final int wrong = 2;
        final JSONObject json = (JSONObject) new JSONParser().parse(
            new SimpleResult(total, answered, correct, wrong).toJsonString()
        );
        assertEquals(total, Integer.parseInt(json.get("total").toString()));
        assertEquals(
            answered, Integer.parseInt(json.get("answered").toString())
        );
        assertEquals(correct, Integer.parseInt(json.get("correct").toString()));
        assertEquals(wrong, Integer.parseInt(json.get("wrong").toString()));
    }

    @Test
    void toDisplayableString() {
        final String partial = new SimpleResult(7, 6, 4, 2)
            .toDisplayableString();
        assertTrue(partial.contains("YOU TEST IS NOT FINISHED."));
        assertTrue(partial.contains("TOTAL: 7"));
        assertTrue(partial.contains("ANSWERED: 6"));
        assertTrue(partial.contains("CORRECT: 4"));
        assertTrue(partial.contains("WRONG: 2"));
        assertTrue(partial.contains("PLEASE CONTINUE."));
        final String failed = new SimpleResult(7, 7, 5, 2)
            .toDisplayableString();
        assertTrue(failed.contains("YOUR TEST IS FINISHED."));
        assertTrue(failed.contains("CORRECT: 5"));
        assertTrue(failed.contains("WRONG: 2"));
        assertTrue(failed.contains("PREPARE AND TRY AGAIN LATER"));
        final String success = new SimpleResult(7, 7, 7, 0)
            .toDisplayableString();
        assertTrue(success.contains("YOUR TEST IS FINISHED."));
        assertTrue(success.contains("CORRECT: 7"));
        assertTrue(success.contains("WRONG: 0"));
        assertTrue(success.contains("CONGRATULATIONS!!!"));
    }
}
