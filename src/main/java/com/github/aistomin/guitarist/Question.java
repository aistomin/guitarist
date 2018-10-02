package com.github.aistomin.guitarist;

/**
 * Created by aistomin on 02.10.18.
 */
public interface Question extends Displayable {

    /**
     * Answer the question with the answer.
     *
     * @param answer The answer.
     */
    void answer(Answer answer);

    /**
     * Is the question answered correctly?
     *
     * @return True - correct; False - isn't correct.
     */
    Boolean isCorrect();

    /**
     * Is the question answered?
     *
     * @return True - answered; False - isn't answered.
     */
    Boolean isAnswered();
}
