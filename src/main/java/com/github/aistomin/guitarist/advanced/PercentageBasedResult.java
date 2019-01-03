package com.github.aistomin.guitarist.advanced;

import com.github.aistomin.guitarist.Result;
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
     * @param inner      Inner result which holds raw data of
     *                   answered/unanswered questions.
     * @param percentage The percentage of the correct answers which must be
     *                   reached to pass the test.
     */
    public PercentageBasedResult(final Result inner, final Integer percentage) {
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

    @Override
    public String toJsonString() throws ParseException {
        return null;
    }

    @Override
    public String toDisplayableString() {
        return null;
    }

    /**
     * Load the value from the inner result's JSON
     *
     * @param key The property's key.
     * @return The value.
     */
    private Integer innerValue(final String key) {
        try {
            final JSONObject json = (JSONObject) new JSONParser().parse(
                this.inner.toJsonString()
            );
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
}
