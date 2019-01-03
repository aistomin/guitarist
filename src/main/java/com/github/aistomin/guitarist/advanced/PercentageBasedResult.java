package com.github.aistomin.guitarist.advanced;

import com.github.aistomin.guitarist.Result;
import com.github.aistomin.guitarist.simple.SimpleResult;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by aistomin on 2019-01-03.
 * <p>
 * The result which allows to pass the test if certain percentage of the answers
 * is correct.
 */
public final class PercentageBasedResult implements Result {

    /**
     * Inner result which holds raw data of answered/unanswered questions.
     */
    private final Result inner;

    /**
     * The percentage of the correct answers which must be reached to pass the
     * test.
     */
    private final Integer percentage;

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
        this(new SimpleResult(total, answered, correct, wrong), percentage);
    }

    /**
     * Ctor.
     *
     * @param inner      Inner result which holds raw data of
     *                   answered/unanswered questions.
     * @param percentage The percentage of the correct answers which must be
     *                   reached to pass the test.
     */
    public PercentageBasedResult(final Result inner, final Integer percentage) {
        if (percentage == null) {
            throw new IllegalArgumentException(
                "All the constructor parameters must be provided."
            );
        }
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException(
                "'percentage' parameter must be between 0 and 100."
            );
        }
        this.inner = inner;
        this.percentage = percentage;
    }

    @Override
    public Boolean isFinished() {
        return this.inner.isFinished();
    }

    @Override
    public Boolean isPassed() {
        return isFinished() &&
            (innerValue("correct") * 100) / innerValue("total") >= percentage;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String toJsonString() throws ParseException {
        final Map<String, String> json = new HashMap<String, String>(innerJson());
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
                    innerValue("total"), innerValue("answered")
                )
            );
            builder.append("\n");
        }
        builder.append(String.format("CORRECT: %d", innerValue("correct")));
        builder.append("\n");
        builder.append(String.format("WRONG: %d", innerValue("wrong")));
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

    /**
     * Load the value from the inner result's JSON
     *
     * @param key The property's key.
     * @return The value.
     */
    private Integer innerValue(final String key) {
        try {
            final JSONObject json = innerJson();
            if (!json.containsKey(key)) {
                throw new IllegalArgumentException(
                    String.format(
                        "Key '%s' is missing in the inner result.", key
                    )
                );
            }
            return Integer.parseInt(((String) json.get(key)));
        } catch (final ParseException error) {
            throw new IllegalStateException(
                String.format(
                    "Can not get key '%s' from the inner result.", key
                ), error
            );
        }
    }

    /**
     * Get the inner result's JSON.
     *
     * @return The JSON object.
     * @throws ParseException If JSON was not successfully parsed.
     */
    private JSONObject innerJson() throws ParseException {
        return (JSONObject) new JSONParser().parse(this.inner.toJsonString());
    }
}
