package com.github.aistomin.guitarist.simple;

import com.github.aistomin.guitarist.Result;
import java.util.HashMap;
import org.json.simple.JSONObject;

/**
 * Created by aistomin on 02.10.18.
 * <p>
 * Simple test's result.
 */
public final class SimpleResult implements Result {

    /**
     * The total amount of questions in the test.
     */
    private final Integer total;

    /**
     * The amount of answered questions in the test.
     */
    private final Integer answered;

    /**
     * The amount of correctly answered questions in the test.
     */
    private final Integer correct;

    /**
     * The amount of wrongly answered questions in the test.
     */
    private final Integer wrong;

    /**
     * Ctor.
     *
     * @param total    The total amount of questions in the test.
     * @param answered The amount of answered questions in the test.
     * @param correct  The amount of correctly answered questions in the test.
     * @param wrong    The amount of wrongly answered questions in the test.
     */
    public SimpleResult(
        final Integer total,
        final Integer answered,
        final Integer correct,
        final Integer wrong
    ) {
        if (
            total == null || answered == null ||
                correct == null || wrong == null
        ) {
            throw new IllegalArgumentException(
                "All the constructor parameters must be provided."
            );
        }
        if (
            total < 0 || answered < 0 ||
                correct < 0 || wrong < 0
        ) {
            throw new IllegalArgumentException(
                "All the constructor parameters must be positive."
            );
        }
        if (
            total < answered || answered != (correct + wrong)
        ) {
            throw new IllegalArgumentException(
                "Constructor parameters must not contradict the common sense."
            );
        }
        this.total = total;
        this.answered = answered;
        this.correct = correct;
        this.wrong = wrong;
    }

    public Boolean isFinished() {
        return total.equals(answered);
    }

    public Boolean isPassed() {
        return total.equals(correct);
    }

    public String toJsonString() {
        final HashMap<String, String> json = new HashMap<String, String>();
        json.put("total", total.toString());
        json.put("answered", answered.toString());
        json.put("correct", correct.toString());
        json.put("wrong", wrong.toString());
        return new JSONObject(json).toJSONString();
    }

    public String toDisplayableString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append("**********************************");
        builder.append("\n");
        if (isFinished()) {
            builder.append("YOUR TEST IS FINISHED.");
            builder.append("\n");
        } else {
            builder.append(
                String.format(
                    "YOU TEST IS NOT FINISHED. \nTOTAL: %d, \nANSWERED: %d",
                    total, answered
                )
            );
            builder.append("\n");
        }
        builder.append(String.format("CORRECT: %d", correct));
        builder.append("\n");
        builder.append(String.format("WRONG: %d", wrong));
        builder.append("\n");
        if (isPassed()) {
            builder.append(":) CONGRATULATIONS!!! :)");
            builder.append("\n");
        } else if (isFinished()) {
            builder.append(":( PREPARE AND TRY AGAIN LATER :(");
            builder.append("\n");
        } else {
            builder.append("PLEASE CONTINUE.");
            builder.append("\n");
        }
        builder.append("**********************************");
        return builder.toString();
    }
}
