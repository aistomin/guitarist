package com.github.aistomin.guitarist.simple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

/**
 * Created by aistomin on 02.10.18.
 * <p>
 * The tests for {@link SimpleQuestion}
 */
final class SimpleQuestionTest {

    /**
     * Check that we can correctly answer the questions.
     */
    @Test
    void answer() {
        final SimpleAnswer expected = new SimpleAnswer("Andrej");
        final SimpleQuestion wrong = new SimpleQuestion(
            "What is your name?", expected
        );
        assertEquals(expected, wrong.help());
        assertFalse(wrong.isAnswered());
        assertFalse(wrong.isCorrect());
        wrong.answer(new SimpleAnswer("John"));
        assertTrue(wrong.isAnswered());
        assertFalse(wrong.isCorrect());
        assertEquals(
            "Can not answer the same question twice.",
            assertThrows(
                IllegalStateException.class,
                () -> wrong.answer(new SimpleAnswer("Alex"))
            ).getMessage()
        );
        final String answer = "Istomin";
        final SimpleQuestion correct = new SimpleQuestion(
            "What is your surname?", new SimpleAnswer(answer)
        );
        assertFalse(correct.isAnswered());
        assertFalse(correct.isCorrect());
        correct.answer(new SimpleAnswer(answer));
        assertTrue(correct.isAnswered());
        assertTrue(correct.isCorrect());
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

    /**
     * Check that we correctly display the question.
     */
    @Test
    void toDisplayableString() {
        final String question = "How old are you?";
        final String answer = "33";
        final SimpleQuestion test = new SimpleQuestion(
            question, new SimpleAnswer(answer)
        );
        final String unanswered = test.toDisplayableString();
        assertTrue(unanswered.contains(question));
        assertFalse(unanswered.contains(answer));
        final String wrong = "15";
        test.answer(new SimpleAnswer(wrong));
        final String answered = test.toDisplayableString();
        assertTrue(answered.contains(question));
        assertTrue(answered.contains(answer));
        assertTrue(answered.contains(wrong));
    }
}
