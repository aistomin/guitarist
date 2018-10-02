package com.github.aistomin.guitarist.simple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.github.aistomin.guitarist.Question;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.jupiter.api.Test;

/**
 * Created by aistomin on 02.10.18.
 * <p>
 * The tests for {@link SimpleTest}
 */
final class SimpleTestTest {

    /**
     * Check that test can be passed or failed.
     */
    @Test
    void testRandom() {
        final ArrayList<Question> questions = questions();
        final SimpleTest test = new SimpleTest(() -> questions);
        int total = 0;
        int correct = 0;
        while (test.hasMoreQuestions()) {
            total++;
            final Question question = test.nextQuestion();
            final ArrayList<Question> shuffle = new ArrayList<>(questions);
            Collections.shuffle(shuffle);
            question.answer(shuffle.get(0).help());
            if (question.isCorrect()) {
                correct++;
            }
        }
        assertTrue(test.currentTestResult().isFinished());
        assertEquals(total == correct, test.currentTestResult().isPassed());
    }

    /**
     * Check that test can be passed correctly.
     */
    @Test
    void testPassed() {
        final ArrayList<Question> questions = questions();
        final SimpleTest test = new SimpleTest(() -> questions);
        for (Question item : questions) {
            final Question question = test.nextQuestion();
            question.answer(item.help());
            assertTrue(question.isCorrect());
        }
        assertTrue(test.currentTestResult().isFinished());
        assertTrue(test.currentTestResult().isPassed());
    }

    /**
     * Generate test questions.
     *
     * @return Test questions' list.
     */
    private ArrayList<Question> questions() {
        final ArrayList<Question> questions = new ArrayList<>();
        questions.add(
            new SimpleQuestion("1 + 1 = ?", new SimpleAnswer("2"))
        );
        questions.add(
            new SimpleQuestion("3 + 6 = ?", new SimpleAnswer("9"))
        );
        questions.add(
            new SimpleQuestion("6 - 2 = ?", new SimpleAnswer("4"))
        );
        questions.add(
            new SimpleQuestion("2 * 2 = ?", new SimpleAnswer("4"))
        );
        questions.add(
            new SimpleQuestion("6 / 2 = ?", new SimpleAnswer("3"))
        );
        return questions;
    }
}
