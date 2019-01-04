package com.github.aistomin.guitarist.simple;

import com.github.aistomin.guitarist.Question;
import com.github.aistomin.guitarist.QuestionsProvider;
import com.github.aistomin.guitarist.Result;
import com.github.aistomin.guitarist.Test;
import com.github.aistomin.guitarist.advanced.PercentageBasedResult;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aistomin on 02.10.18.
 * <p>
 * The simple implementation of {@link Test}
 */
public final class SimpleTest implements Test {

    /**
     * The list of the questions of the test.
     */
    private final List<Question> questions;

    /**
     * Ctor.
     *
     * @param questions Questions provider.
     */
    public SimpleTest(final QuestionsProvider questions) {
        this.questions = questions.questions();
    }

    public synchronized Boolean hasMoreQuestions() {
        return nextIndex() < questions.size();
    }

    public synchronized Question nextQuestion() {
        return questions.get(nextIndex());
    }

    public synchronized Result currentTestResult() {
        final List<Question> correct = new ArrayList<>();
        final List<Question> wrong = new ArrayList<>();
        for (Question question : questions) {
            if (question.isAnswered()) {
                if (question.isCorrect()) {
                    correct.add(question);
                } else {
                    wrong.add(question);
                }
            }
        }
        final int cor = correct.size();
        final int wrg = wrong.size();
        return new PercentageBasedResult(
            questions.size(), cor + wrg, cor, wrg
        );
    }

    /**
     * The next question's index.
     *
     * @return The index.
     */
    private Integer nextIndex() {
        int index = questions.size();
        for (int i = 0; i < questions.size(); i++) {
            if (!questions.get(i).isAnswered()) {
                index = i;
                break;
            }
        }
        return index;
    }
}
