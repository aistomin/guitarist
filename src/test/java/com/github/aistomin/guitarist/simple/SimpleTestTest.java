package com.github.aistomin.guitarist.simple;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.github.aistomin.guitarist.Question;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        final TestQuestionsProvider provider = new TestQuestionsProvider();
        final List<Question> questions = provider.questions();
        final SimpleTest test = new SimpleTest(provider);
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
        final TestQuestionsProvider provider = new TestQuestionsProvider();
        final List<Question> questions = provider.questions();
        final SimpleTest test = new SimpleTest(provider);
        for (Question item : questions) {
            final Question question = test.nextQuestion();
            question.answer(item.help());
            assertTrue(question.isCorrect());
        }
        assertTrue(test.currentTestResult().isFinished());
        assertTrue(test.currentTestResult().isPassed());
    }
}
