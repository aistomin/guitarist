package com.github.aistomin.guitarist.tests.fretboard;

import com.github.aistomin.guitarist.Answer;
import com.github.aistomin.guitarist.Question;
import com.github.aistomin.guitarist.domain.Fretboard;
import com.github.aistomin.guitarist.domain.Note;
import com.github.aistomin.guitarist.simple.SimpleAnswer;
import com.github.aistomin.guitarist.simple.SimpleQuestion;
import org.json.simple.parser.ParseException;

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
            String.format(
                "Which note do we have on the %s string fret #%d?",
                string.helmholtzName(), position
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
    public String toJsonString() throws ParseException {
        return simple.toJsonString();
    }

    @Override
    public String toDisplayableString() {
        return simple.toDisplayableString();
    }
}
