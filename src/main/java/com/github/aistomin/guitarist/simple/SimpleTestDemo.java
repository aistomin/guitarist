package com.github.aistomin.guitarist.simple;

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
        new SimpleTestConsole(
            new SimpleTest(
                new TestQuestionsProvider()
            )
        ).runTest();
    }
}
