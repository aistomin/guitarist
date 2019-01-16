package com.github.aistomin.guitarist.multichoice;

import static org.junit.jupiter.api.Assertions.*;
import com.github.aistomin.guitarist.simple.SimpleAnswer;
import com.github.aistomin.guitarist.simple.SimpleQuestion;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

/**
 * Created by aistomin on 2019-01-16.
 * <p>
 * The test for {@link MultiChoiceQuestion}
 */
class MultiChoiceQuestionTest {

    @Test
    void answer() {
    }

    /**
     * Check that we correctly convert question to JSON string.
     *
     * @throws ParseException On JSON parsing error.
     */
    @Test
    void toJsonString() throws ParseException {
        final String question = "Who are you?";
        final String answer = "It's me";
        final SimpleQuestion test = new SimpleQuestion(
            question, new SimpleAnswer(answer)
        );
        final JSONObject unanswered =
            (JSONObject) new JSONParser().parse(test.toJsonString());
        assertEquals(question, unanswered.get("text"));
        assertEquals(
            answer, ((JSONObject) unanswered.get("expected")).get("text")
        );
        assertNull(unanswered.get("got"));
        final String wrong = "It's he";
        test.answer(new SimpleAnswer(wrong));
        final JSONObject answered =
            (JSONObject) new JSONParser().parse(test.toJsonString());
        assertEquals(question, answered.get("text"));
        assertEquals(
            answer, ((JSONObject) answered.get("expected")).get("text")
        );
        assertEquals(wrong, ((JSONObject) answered.get("got")).get("text"));
    }

    @Test
    void toDisplayableString() {
    }
}