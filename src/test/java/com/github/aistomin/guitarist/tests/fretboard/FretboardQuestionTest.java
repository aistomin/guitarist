package com.github.aistomin.guitarist.tests.fretboard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.github.aistomin.guitarist.Question;
import com.github.aistomin.guitarist.domain.Guitar6Fretboard;
import com.github.aistomin.guitarist.domain.Note;
import com.github.aistomin.guitarist.simple.SimpleAnswer;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

/**
 * Created by aistomin on 10.10.18.
 */
class FretboardQuestionTest {

    /**
     * Check that we can correctly answer the question.
     */
    @Test
    void answer() {
        final Question first = new FretboardQuestion(
            Note.b, 12, new Guitar6Fretboard()
        );
        assertFalse(first.isAnswered());
        assertFalse(first.isCorrect());
        first.answer(new SimpleAnswer("b'"));
        assertTrue(first.isAnswered());
        assertTrue(first.isCorrect());
        final Question second = new FretboardQuestion(
            Note.e_, 8, new Guitar6Fretboard()
        );
        assertFalse(second.isAnswered());
        assertFalse(second.isCorrect());
        second.answer(new SimpleAnswer("c'"));
        assertTrue(second.isAnswered());
        assertFalse(second.isCorrect());
    }

    /**
     * Check that correct answer is returned on help call.
     */
    @Test
    void help() {
        final Question question = new FretboardQuestion(
            Note.g, 0, new Guitar6Fretboard()
        );
        assertNotNull(question.help());
        question.answer(question.help());
        assertTrue(question.isCorrect());
    }

    /**
     * Check the JSON serialisation works.
     *
     * @throws ParseException If JSON error occurred.
     */
    @Test
    void toJsonString() throws ParseException {
        assertTrue(
            new FretboardQuestion(Note.A, 2, new Guitar6Fretboard())
                .toJsonString()
                .contains("Which note do we have on the A string fret #2?")
        );
    }

    /**
     * Check that we can display the question.
     */
    @Test
    void toDisplayableString() {
        assertTrue(
            new FretboardQuestion(Note.E, 5, new Guitar6Fretboard())
                .toDisplayableString()
                .contains("Which note do we have on the E string fret #5?")
        );
    }
}
