package com.github.aistomin.guitarist.multichoice;

import com.github.aistomin.guitarist.Answer;
import com.github.aistomin.guitarist.Question;
import com.github.aistomin.guitarist.simple.SimpleQuestion;
import java.util.Map;
import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by aistomin on 2019-01-16.
 * <p>
 * The multi-choice test implementation of the {@link Question}. Actually it
 * is a decorator for {@link SimpleQuestion} which accepts multi-choice answer.
 */
public class MultiChoiceQuestion implements Question {

    /**
     * The choices that will be displayed to the user.
     */
    private final Map<Choice, String> choices;

    /**
     * Simple question which we decorate.
     */
    private final SimpleQuestion simple;

    /**
     * Ctor.
     *
     * @param text    The question's text.
     * @param choices The choices that will be displayed to the user.
     * @param correct The correct choices.
     */
    public MultiChoiceQuestion(
        final String text, final Map<Choice, String> choices, final Set<Choice> correct
    ) {
        this(text, choices, new MultiChoiceAnswer(correct));
    }

    public MultiChoiceQuestion(
        final String text, final Map<Choice, String> choices, final Answer answer
    ) {
        this.choices = choices;
        this.simple = new SimpleQuestion(text, answer);
    }

    @Override
    public void answer(final Answer answer) {
        this.simple.answer(answer);
    }

    @Override
    public Boolean isCorrect() {
        return this.simple.isCorrect();
    }

    @Override
    public Boolean isAnswered() {
        return this.simple.isAnswered();
    }

    @Override
    public Answer help() {
        return this.simple.help();
    }

    @Override
    public String toJsonString() throws ParseException {
        final JSONObject json = (JSONObject) new JSONParser()
            .parse(this.simple.toJsonString());
        json.put("choices", this.choices);
        return json.toJSONString();
    }

    @Override
    public String toDisplayableString() {
        return this.simple.toDisplayableString();
    }
}
