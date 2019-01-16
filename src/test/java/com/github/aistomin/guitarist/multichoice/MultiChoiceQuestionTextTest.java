package com.github.aistomin.guitarist.multichoice;

import static com.github.aistomin.guitarist.multichoice.Choice.A;
import static com.github.aistomin.guitarist.multichoice.Choice.B;
import static com.github.aistomin.guitarist.multichoice.Choice.C;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

/**
 * Created by aistomin on 2019-01-16.
 * <p>
 * The test for {@link MultiChoiceQuestionText}
 */
final class MultiChoiceQuestionTextTest {

    /**
     * Check that we correctly display the text.
     */
    @Test
    void toDisplayableString() {
        final Map<Choice, String> choices = new HashMap<>();
        choices.put(A, "Andrej");
        choices.put(B, "John");
        choices.put(C, "Tom");
        assertEquals(
            "What is your name?\n\nA. Andrej\nB. John\nC. Tom",
            new MultiChoiceQuestionText("What is your name?", choices)
                .toDisplayableString()
        );
    }

    /**
     * Check that we correctly convert the test to the JSON.
     */
    @SuppressWarnings("unchecked")
    @Test
    void toJson() {
        final Map<Choice, String> expected = new HashMap<>();
        expected.put(A, "Java");
        expected.put(B, "PHP");
        expected.put(C, "C++");
        final String question = "What is your favourite programming language?";
        final JSONObject json = new MultiChoiceQuestionText(question, expected)
            .toJson();
        assertEquals(question, json.get("text"));
        Map<Choice, String> got = ((Map<Choice, String>) json.get("choices"));
        assertEquals(expected.get(A), got.get(A));
        assertEquals(expected.get(B), got.get(B));
        assertEquals(expected.get(C), got.get(C));
    }
}
