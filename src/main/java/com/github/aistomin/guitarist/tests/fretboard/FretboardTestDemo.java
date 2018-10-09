package com.github.aistomin.guitarist.tests.fretboard;

import com.github.aistomin.guitarist.Question;
import com.github.aistomin.guitarist.domain.Fretboard;
import com.github.aistomin.guitarist.domain.Guitar6Fretboard;
import com.github.aistomin.guitarist.domain.Note;
import com.github.aistomin.guitarist.simple.SimpleTest;
import com.github.aistomin.guitarist.simple.SimpleTestConsole;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by aistomin on 08.10.18.
 * <p>
 * The example of the fretboard test.
 */
public final class FretboardTestDemo {

    /**
     * Runnable method.
     *
     * @param args Arguments.
     */
    public static void main(final String[] args) {
        final Fretboard fretboard = new Guitar6Fretboard();
        final Random random = new Random();
        new SimpleTestConsole(
            new SimpleTest(
                () -> {
                    final ArrayList<Question> questions = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        final List<Note> strings = fretboard.strings();
                        Collections.shuffle(strings);
                        questions.add(
                            new FretboardQuestion(
                                strings.get(0), random.nextInt(13), fretboard
                            )
                        );
                    }
                    return questions;
                }
            )
        ).runTest();
    }
}
