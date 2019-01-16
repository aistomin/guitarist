package com.github.aistomin.guitarist.simple;

import com.github.aistomin.guitarist.Answer;
import com.github.aistomin.guitarist.Question;
import com.github.aistomin.guitarist.QuestionsText;
import java.util.HashMap;
import org.json.simple.JSONObject;

/**
 * Created by aistomin on 02.10.18.
 */
public final class SimpleQuestion implements Question {

    /**
     * Question's text.
     */
    private final QuestionsText text;

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
     * @param text   Question's text.
     * @param answer Expected answer to the question.
     */
    public SimpleQuestion(final QuestionsText text, final Answer answer) {
        this.text = text;
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
    public Answer help() {
        return expected;
    }

    @Override
    public synchronized JSONObject toJson() {
        final HashMap<String, Object> json = new HashMap<>();
        json.put("question", text.toJson());
        json.put("expected", expected.toJson());
        if (isAnswered()) {
            json.put("got", got.toJson());
        }
        return new JSONObject(json);
    }

    @Override
    public synchronized String toDisplayableString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append("**********************************");
        builder.append("\n");
        builder.append(text.toDisplayableString());
        builder.append("\n");
        if (isAnswered()) {
            if (isCorrect()) {
                builder.append("YOUR ANSWER IS CORRECT!");
                builder.append("\n");
                builder.append(
                    String.format("ANSWER: %s", expected.toDisplayableString())
                );
                builder.append("\n");
            } else {
                builder.append("YOUR ANSWER IS NOT CORRECT!");
                builder.append("\n");
                builder.append(
                    String.format(
                        "CORRECT ANSWER: %s", expected.toDisplayableString()
                    )
                );
                builder.append("\n");
                builder.append(
                    String.format(
                        "PROVIDED ANSWER: %s", got.toDisplayableString()
                    )
                );
                builder.append("\n");
            }
        }
        builder.append("**********************************");
        return builder.toString();
    }
}
