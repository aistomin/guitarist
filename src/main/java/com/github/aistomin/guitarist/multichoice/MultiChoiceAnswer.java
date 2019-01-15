package com.github.aistomin.guitarist.multichoice;

import com.github.aistomin.guitarist.Answer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.json.simple.JSONObject;

/**
 * Created by aistomin on 2019-01-15.
 * <p>
 * The multi-choice test implementation of the {@link Answer}
 */
public class MultiChoiceAnswer implements Answer {

    /**
     * The options selected by user.
     */
    private final Set<String> selected;

    /**
     * Ctor.
     *
     * @param selected The options selected by user.
     */
    public MultiChoiceAnswer(final Set<String> selected) {
        this.selected = selected;
    }

    @Override
    public Boolean validate(final Answer answer) {
        return answer != null &&
            this.toDisplayableString().equals(answer.toDisplayableString());
    }

    @Override
    public String toJsonString() {
        final HashMap<String, String> json = new HashMap<>();
        json.put("text", this.toDisplayableString());
        return new JSONObject(json).toJSONString();
    }

    @Override
    public String toDisplayableString() {
        final List<String> sorted = new ArrayList<>(selected);
        sorted.sort(Comparator.naturalOrder());
        return String.join("; ", sorted);
    }
}
