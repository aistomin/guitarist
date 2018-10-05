package com.github.aistomin.guitarist.simple;

import com.github.aistomin.guitarist.Question;
import com.github.aistomin.guitarist.Test;
import java.util.Scanner;

/**
 * Created by aistomin on 05.10.18.
 * <p>
 * Encapsulates the logic which interacts with user.
 */
public final class SimpleTestConsole {

    /**
     * The test which is going to be launched in console.
     */
    private final Test test;

    /**
     * Ctor.
     *
     * @param test The test which is going to be launched in console.
     */
    public SimpleTestConsole(final Test test) {
        this.test = test;
    }

    /**
     * Run the test in the console.
     */
    public void runTest() {
        final Scanner scanner = new Scanner(System.in);
        while (test.hasMoreQuestions()) {
            final Question question = test.nextQuestion();
            System.out.println(question.toDisplayableString());
            question.answer(new SimpleAnswer(scanner.next()));
            System.out.println(question.toDisplayableString());
        }
        System.out.println(test.currentTestResult().toDisplayableString());
    }
}
