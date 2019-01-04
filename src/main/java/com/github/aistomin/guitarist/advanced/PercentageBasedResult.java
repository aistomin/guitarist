package com.github.aistomin.guitarist.advanced;

import com.github.aistomin.guitarist.Result;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 * Created by aistomin on 2019-01-03.
 * <p>
 * The result which allows to pass the test if certain percentage of the answers
 * is correct.
 */
public final class PercentageBasedResult implements Result {

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
     * The percentage of the correct answers which must be reached to pass the
     * test.
     */
    private final Integer percentage;

    /**
     * Ctor. The percentage is 100% in this case.
     *
     * @param total    The total amount of questions in the test.
     * @param answered The amount of answered questions in the test.
     * @param correct  The amount of correctly answered questions in the test.
     * @param wrong    The amount of wrongly answered questions in the test.
     */
    public PercentageBasedResult(
        final Integer total,
        final Integer answered,
        final Integer correct,
        final Integer wrong
    ) {
        this(total, answered, correct, wrong, 100);
    }

    /**
     * Ctor.
     *
     * @param total      The total amount of questions in the test.
     * @param answered   The amount of answered questions in the test.
     * @param correct    The amount of correctly answered questions in the test.
     * @param wrong      The amount of wrongly answered questions in the test.
     * @param percentage The percentage of the correct answers which must be
     *                   reached to pass the test.
     */
    public PercentageBasedResult(
        final Integer total,
        final Integer answered,
        final Integer correct,
        final Integer wrong,
        final Integer percentage
    ) {
        if (
            total == null || answered == null ||
                correct == null || wrong == null || percentage == null
        ) {
            throw new IllegalArgumentException(
                "All the constructor parameters must be provided."
            );
        }
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException(
                "'percentage' parameter must be between 0 and 100."
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
        this.percentage = percentage;
    }

    @Override
    public Boolean isFinished() {
        return total.equals(answered);
    }

    @Override
    public Boolean isPassed() {
        return isFinished() &&
            (this.correct * 100) / this.total >= percentage;
    }

    @Override
    public String toJsonString() {
        final Map<String, String> json = new HashMap<>();
        json.put("total", total.toString());
        json.put("answered", answered.toString());
        json.put("correct", correct.toString());
        json.put("wrong", wrong.toString());
        json.put("percentage", percentage.toString());
        return new JSONObject(json).toJSONString();
    }

    @Override
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
                    this.total, this.answered
                )
            );
            builder.append("\n");
        }
        builder.append(String.format("CORRECT: %d", this.correct));
        builder.append("\n");
        builder.append(String.format("WRONG: %d", this.wrong));
        builder.append("\n");
        builder.append(String.format("PASSING PERCENTAGE: %d", this.percentage));
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
