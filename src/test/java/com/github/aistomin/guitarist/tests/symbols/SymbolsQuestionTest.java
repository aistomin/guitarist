package com.github.aistomin.guitarist.tests.symbols;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.github.aistomin.guitarist.domain.MusicSymbol;
import com.github.aistomin.guitarist.simple.SimpleAnswer;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

/**
 * Created by aistomin on 05.10.18.
 * <p>
 * The tests for {@link SymbolsQuestion}
 */
class SymbolsQuestionTest {

    /**
     * Check that we can correctly answer the question.
     */
    @Test
    void answer() {
        final MusicSymbol symbol = MusicSymbol.random();
        final SymbolsQuestion first = new SymbolsQuestion(symbol);
        assertFalse(first.isAnswered());
        assertFalse(first.isCorrect());
        first.answer(first.help());
        assertTrue(first.isAnswered());
        assertTrue(first.isCorrect());
        final SymbolsQuestion second = new SymbolsQuestion(symbol);
        assertFalse(second.isAnswered());
        assertFalse(second.isCorrect());
        second.answer(new SimpleAnswer("12"));
        assertTrue(second.isAnswered());
        assertFalse(second.isCorrect());
    }

    /**
     * Check that correct answer is returned on help call.
     */
    @Test
    void help() {
        final MusicSymbol symbol = MusicSymbol.random();
        final SymbolsQuestion question = new SymbolsQuestion(symbol);
        assertNotNull(question.help());
        question.answer(question.help());
        assertTrue(question.isCorrect());
    }

    /**
     * Check the JSON serialisation works.
     *
     * @throws ParseException If JSON error occurred.
     */
    @Test
    void toJsonString() throws ParseException {
        final MusicSymbol symbol = MusicSymbol.random();
        assertTrue(
            new SymbolsQuestion(symbol).toJsonString()
                .contains(symbol.description())
        );
    }

    /**
     * Check that we can display the question.
     */
    @Test
    void toDisplayableString() {
        assertTrue(
            new SymbolsQuestion(MusicSymbol.NATURAL_NOTE)
                .toDisplayableString().contains("What does \"♮\" symbol mean?")
        );
    }
}
