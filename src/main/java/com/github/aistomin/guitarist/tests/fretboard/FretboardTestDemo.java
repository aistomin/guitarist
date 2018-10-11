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
import java.util.Scanner;

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
        final Scanner scanner = new Scanner(System.in);
        int fretsInput = 0;
        while (fretsInput == 0) {
            System.out.println("How many frets do you want to test?");
            try {
                fretsInput = Integer.parseInt(scanner.next());
            } catch (final Throwable ignored) {
            }
            if (fretsInput <= 0 || fretsInput > 12) {
                System.out.println(
                    "Error. Frets count must be bigger than 0 and less then 13"
                );
                fretsInput = 0;
            }
        }
        int questionsInput = 0;
        while (questionsInput == 0) {
            System.out.println("How many questions do you want to be asked?");
            try {
                questionsInput = Integer.parseInt(scanner.next());
            } catch (final Throwable ignored) {
            }
            if (questionsInput <= 0) {
                System.out.println(
                    "Error. Questions count must be positive number."
                );
                questionsInput = 0;
            }
        }
        final Fretboard fretboard = new Guitar6Fretboard();
        final Random random = new Random();
        final int frets = fretsInput;
        final int quests = questionsInput;
        new SimpleTestConsole(
            new SimpleTest(
                () -> {
                    final ArrayList<Question> questions = new ArrayList<>();
                    for (int i = 0; i < quests; i++) {
                        final List<Note> strings = fretboard.strings();
                        Collections.shuffle(strings);
                        questions.add(
                            new FretboardQuestion(
                                strings.get(0), random.nextInt(frets), fretboard
                            )
                        );
                    }
                    return questions;
                }
            )
        ).runTest();
    }
}
