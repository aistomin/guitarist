package com.github.aistomin.guitarist;

import java.util.List;

/**
 * Created by aistomin on 02.10.18.
 * <p>
 * The interface of classes which can somehow provide the questions.
 */
public interface QuestionsProvider {

    /**
     * Load a list of questions.
     *
     * @return The questions.
     */
    List<Question> questions();
}
