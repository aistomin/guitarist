package com.github.aistomin.guitarist.multichoice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashSet;
import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

/**
 * Created by aistomin on 2019-01-15.
 * <p>
 * The test for {@link MultiChoiceAnswer}
 */
final class MultiChoiceAnswerTest {

    /**
     * Check that we correctly validate the answers.
     */
    @Test
    void validate() {
        final Set<String> expected = new HashSet<>();
        expected.add("G");
        expected.add("F");
        expected.add("E");
        final Set<String> correct = new HashSet<>();
        correct.add("F");
        correct.add("E");
        correct.add("G");
        final MultiChoiceAnswer answer = new MultiChoiceAnswer(expected);
        assertTrue(answer.validate(new MultiChoiceAnswer(correct)));
        final Set<String> incorrect = new HashSet<>();
        incorrect.add("F");
        incorrect.add("L");
        incorrect.add("G");
        assertFalse(answer.validate(new MultiChoiceAnswer(incorrect)));
        assertFalse(answer.validate(null));
    }

    /**
     * Check that we correctly convert the answer to the JSON string.
     *
     * @throws ParseException On JSON parsing exception.
     */
    @Test
    void toJsonString() throws ParseException {
        final Set<String> selected = new HashSet<>();
        selected.add("C");
        selected.add("D");
        assertEquals(
            "C; D",
            (
                (JSONObject) new JSONParser()
                    .parse(new MultiChoiceAnswer(selected).toJsonString())
            ).get("text")
        );
    }

    /**
     * Check that we correctly display the answer.
     */
    @Test
    void toDisplayableString() {
        final Set<String> selected = new HashSet<>();
        selected.add("A");
        selected.add("F");
        selected.add("B");
        assertEquals(
            "A; B; F", new MultiChoiceAnswer(selected).toDisplayableString()
        );
    }
}