package com.github.aistomin.guitarist.simple;

import com.github.aistomin.guitarist.Answer;
import com.github.aistomin.guitarist.Question;

/**
 * Created by aistomin on 02.10.18.
 */
public class SimpleQuestion implements Question {

    /**
     * Expected answer.
     */
    private final Answer expected;

    /**
     * The answer which we got from the client.
     */
    private Answer got;

    /**
     * Ctor.
     *
     * @param answer Expected answer to the question.
     */
    public SimpleQuestion(final Answer answer) {
        this.expected = answer;
    }

    @Override
    public synchronized void answer(final Answer answer) {
        if (got != null) {
            throw new IllegalStateException("Can not answer the same question twice.");
        }
        got = answer;
    }

    @Override
    public synchronized Boolean isCorrect() {
        return isAnswered() && expected.validate(got);
    }

    @Override
    public synchronized Boolean isAnswered() {
        return got != null;
    }

    @Override
    public synchronized String toJsonString() {
        return null;
    }

    @Override
    public synchronized String toDisplayableString() {
        return null;
    }
}
