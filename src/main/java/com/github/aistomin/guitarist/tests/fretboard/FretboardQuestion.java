package com.github.aistomin.guitarist.tests.fretboard;

import com.github.aistomin.guitarist.Answer;
import com.github.aistomin.guitarist.Question;
import com.github.aistomin.guitarist.domain.Fretboard;
import com.github.aistomin.guitarist.domain.Note;
import com.github.aistomin.guitarist.simple.SimpleAnswer;
import com.github.aistomin.guitarist.simple.SimpleQuestion;
import com.github.aistomin.guitarist.simple.SimpleText;
import org.json.simple.JSONObject;

/**
 * Created by aistomin on 10.10.18.
 */
public class FretboardQuestion implements Question {

    /**
     * Simple question which we're decorating.
     */
    private final SimpleQuestion simple;

    /**
     * Ctor.
     *
     * @param string    The string.
     * @param position  The position on the fret.
     * @param fretboard The fretboard.
     */
    public FretboardQuestion(
        final Note string, final Integer position, final Fretboard fretboard
    ) {
        this.simple = new SimpleQuestion(
            new SimpleText(
                String.format(
                    "Which note do we have on the %s string fret #%d?",
                    string.helmholtzName(), position
                )
            ),
            new SimpleAnswer(fretboard.note(string, position).helmholtzName())
        );
    }

    @Override
    public void answer(final Answer answer) {
        this.simple.answer(answer);
    }

    @Override
    public Boolean isCorrect() {
        return simple.isCorrect();
    }

    @Override
    public Boolean isAnswered() {
        return simple.isAnswered();
    }

    @Override
    public Answer help() {
        return simple.help();
    }

    @Override
    public JSONObject toJson() {
        return simple.toJson();
    }

    @Override
    public String toDisplayableString() {
        return simple.toDisplayableString();
    }
}
