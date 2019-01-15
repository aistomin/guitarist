package com.github.aistomin.guitarist.multichoice;

import static com.github.aistomin.guitarist.multichoice.Choice.A;
import static com.github.aistomin.guitarist.multichoice.Choice.B;
import static com.github.aistomin.guitarist.multichoice.Choice.C;
import static com.github.aistomin.guitarist.multichoice.Choice.D;
import static com.github.aistomin.guitarist.multichoice.Choice.E;
import static com.github.aistomin.guitarist.multichoice.Choice.F;
import static com.github.aistomin.guitarist.multichoice.Choice.G;
import static com.github.aistomin.guitarist.multichoice.Choice.L;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
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
        final Set<Choice> expected = new HashSet<>(Arrays.asList(G, F, E));
        final Set<Choice> correct = new HashSet<>(Arrays.asList(F, E, G));
        final MultiChoiceAnswer answer = new MultiChoiceAnswer(expected);
        assertTrue(answer.validate(new MultiChoiceAnswer(correct)));
        final Set<Choice> incorrect = new HashSet<>(Arrays.asList(F, L, G));
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
        final Set<Choice> selected = new HashSet<>(Arrays.asList(C, D));
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
        final Set<Choice> selected = new HashSet<>(Arrays.asList(A, F, B));
        assertEquals(
            "A; B; F", new MultiChoiceAnswer(selected).toDisplayableString()
        );
    }
}
