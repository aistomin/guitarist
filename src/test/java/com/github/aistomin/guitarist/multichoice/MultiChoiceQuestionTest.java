package com.github.aistomin.guitarist.multichoice;

import static com.github.aistomin.guitarist.multichoice.Choice.A;
import static com.github.aistomin.guitarist.multichoice.Choice.B;
import static com.github.aistomin.guitarist.multichoice.Choice.C;
import static com.github.aistomin.guitarist.multichoice.Choice.D;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Created by aistomin on 2019-01-16.
 * <p>
 * The test for {@link MultiChoiceQuestion}
 */
final class MultiChoiceQuestionTest {

    @Test
    void answer() {
        final Map<Choice, String> choices = new HashMap<>();
        choices.put(A, "Yes");
        choices.put(B, "ELP");
        choices.put(C, "Genesis");
        choices.put(D, "Dream Theater");
        final String text = "What is the best prog. rock band?";
        final HashSet<Choice> answer = new HashSet<>(Arrays.asList(B, C));
        final MultiChoiceQuestion correct = new MultiChoiceQuestion(
            text, choices, answer
        );
        assertFalse(correct.isAnswered());
        assertFalse(correct.isCorrect());
        correct.answer(
            new MultiChoiceAnswer(new HashSet<>(Arrays.asList(C, B)))
        );
        assertTrue(correct.isAnswered());
        assertTrue(correct.isCorrect());
        final MultiChoiceQuestion incomplete = new MultiChoiceQuestion(
            text, choices, answer
        );
        assertFalse(incomplete.isAnswered());
        assertFalse(incomplete.isCorrect());
        incomplete.answer(
            new MultiChoiceAnswer(new HashSet<>(Collections.singletonList(B)))
        );
        assertTrue(incomplete.isAnswered());
        assertFalse(incomplete.isCorrect());
        final MultiChoiceQuestion wrong = new MultiChoiceQuestion(
            text, choices, answer
        );
        assertFalse(wrong.isAnswered());
        assertFalse(wrong.isCorrect());
        wrong.answer(
            new MultiChoiceAnswer(new HashSet<>(Arrays.asList(A, D)))
        );
        assertTrue(wrong.isAnswered());
        assertFalse(wrong.isCorrect());
    }
}
