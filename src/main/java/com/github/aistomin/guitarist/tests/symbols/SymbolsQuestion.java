package com.github.aistomin.guitarist.tests.symbols;

import com.github.aistomin.guitarist.Answer;
import com.github.aistomin.guitarist.Question;
import com.github.aistomin.guitarist.domain.MusicSymbol;
import com.github.aistomin.guitarist.simple.SimpleAnswer;
import com.github.aistomin.guitarist.simple.SimpleQuestion;
import com.github.aistomin.guitarist.simple.SimpleText;
import java.util.List;
import org.json.simple.JSONObject;

/**
 * Created by aistomin on 05.10.18.
 * <p>
 * The question of the music symbols test.
 */
public final class SymbolsQuestion implements Question {

    /**
     * Simple question which we're decorating.
     */
    private final SimpleQuestion simple;

    /**
     * Ctor.
     *
     * @param symbol Music symbol which we are asking about.
     */
    public SymbolsQuestion(final MusicSymbol symbol) {
        final StringBuilder question = new StringBuilder();
        question.append(
            String.format(
                "What does \"%s\" symbol mean? Choose the correct number:\n",
                symbol.symbol()
            )
        );
        final List<MusicSymbol> similar = symbol.similar();
        for (int i = 0; i < similar.size(); i++) {
            question.append(
                String.format("[%d] %s\n", i, similar.get(i).description())
            );
        }
        this.simple = new SimpleQuestion(
            new SimpleText(question.toString()),
            new SimpleAnswer(String.valueOf(similar.indexOf(symbol)))
        );
    }

    @Override
    public void answer(final Answer answer) {
        simple.answer(answer);
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
