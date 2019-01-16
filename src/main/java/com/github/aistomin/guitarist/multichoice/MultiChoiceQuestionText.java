package com.github.aistomin.guitarist.multichoice;

import com.github.aistomin.guitarist.QuestionsText;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 * Created by aistomin on 2019-01-16.
 * <p>
 * The multi-choice implementation of the {@link QuestionsText}
 */
public class MultiChoiceQuestionText implements QuestionsText {

    /**
     * The question's text.
     */
    private final String text;

    /**
     * The choices that will be displayed to the user.
     */
    private final Map<Choice, String> choices;

    /**
     * Ctor.
     *
     * @param text    The question's text.
     * @param choices The choices that will be displayed to the user.
     */
    public MultiChoiceQuestionText(
        final String text, final Map<Choice, String> choices
    ) {
        this.text = text;
        this.choices = choices;
    }

    @Override
    public String toDisplayableString() {
        final StringBuilder builder = new StringBuilder(this.text);
        builder.append("\n");
        final List<Choice> keys = new ArrayList<>(this.choices.keySet());
        keys.sort(Comparator.naturalOrder());
        keys.forEach(key -> {
            builder.append("\n");
            builder.append(key.name());
            builder.append(". ");
            builder.append(this.choices.get(key));
        });
        return builder.toString();
    }

    @Override
    public JSONObject toJson() {
        final Map<String, Object> json = new HashMap<>();
        json.put("text", this.text);
        json.put("choices", this.choices);
        return new JSONObject(json);
    }
}
