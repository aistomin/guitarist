package com.github.aistomin.guitarist.tests.symbols;

import com.github.aistomin.guitarist.Question;
import com.github.aistomin.guitarist.domain.MusicSymbol;
import com.github.aistomin.guitarist.simple.SimpleTest;
import com.github.aistomin.guitarist.simple.SimpleTestConsole;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by aistomin on 05.10.18.
 * <p>
 * The example of the symbols test.
 */
public final class SymbolsTestDemo {

    /**
     * Runnable method.
     *
     * @param args Arguments.
     */
    public static void main(final String[] args) {
        new SimpleTestConsole(
            new SimpleTest(
                () -> {
                    final List<MusicSymbol> symbols = Arrays.asList(MusicSymbol.values());
                    Collections.shuffle(symbols);
                    final ArrayList<Question> result = new ArrayList<>();
                    for (int i = 0; i < 5; i++) {
                        result.add(new SymbolsQuestion(symbols.get(i)));
                    }
                    return result;
                }
            )
        ).runTest();
    }
}
