package com.github.aistomin.guitarist.simple;

import com.github.aistomin.guitarist.Question;
import java.util.Scanner;

/**
 * Created by aistomin on 02.10.18.
 * <p>
 * The example of the simple test.
 */
public final class SimpleTestDemo {

    /**
     * Runnable method.
     *
     * @param args Arguments.
     */
    public static void main(final String[] args) {
        final SimpleTest test = new SimpleTest(
            new TestQuestionsProvider()
        );
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
