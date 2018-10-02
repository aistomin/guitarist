package com.github.aistomin.guitarist;

/**
 * Created by aistomin on 02.10.18.
 * <p>
 * Abstract interface of the test.
 */
public interface Test {

    /**
     * Does the test have more questions?
     *
     * @return True - yes; False - no.
     */
    Boolean hasMoreQuestions();

    /**
     * Next test's question.
     *
     * @return The question.
     */
    Question nextQuestion();

    /**
     * Current test's result. It may be final or intermediate result.
     *
     * @return The result.
     */
    Result currentTestResult();
}
