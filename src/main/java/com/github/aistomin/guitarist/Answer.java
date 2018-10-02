package com.github.aistomin.guitarist;

/**
 * Created by aistomin on 02.10.18.
 * <p>
 * The interface of the answer object.
 */
public interface Answer extends Displayable {

    /**
     * Validate this answer with another one.
     *
     * @param answer Another answer.
     * @return True - the answer is correct; False - the answer is not correct.
     */
    Boolean validate(Answer answer);
}
