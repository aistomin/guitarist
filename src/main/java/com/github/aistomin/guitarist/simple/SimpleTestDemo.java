package com.github.aistomin.guitarist.simple;

import com.github.aistomin.guitarist.Question;
import java.util.ArrayList;
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
            () -> {
                final ArrayList<Question> questions = new ArrayList<>();
                questions.add(
                    new SimpleQuestion("1 + 1 = ?", new SimpleAnswer("2"))
                );
                questions.add(
                    new SimpleQuestion("3 + 6 = ?", new SimpleAnswer("9"))
                );
                questions.add(
                    new SimpleQuestion("6 - 2 = ?", new SimpleAnswer("4"))
                );
                questions.add(
                    new SimpleQuestion("2 * 2 = ?", new SimpleAnswer("4"))
                );
                questions.add(
                    new SimpleQuestion("6 / 2 = ?", new SimpleAnswer("3"))
                );
                return questions;
            }
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
