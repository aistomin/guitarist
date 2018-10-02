package com.github.aistomin.guitarist;

/**
 * Created by aistomin on 02.10.18.
 * <p>
 * The interface of the test's result.
 */
public interface Result extends Displayable {

    /**
     * Is test finished or it's just an intermediate result?
     *
     * @return True - the test is finished; False - the intermediate result.
     */
    Boolean isFinished();

    /**
     * Is the test passed or failed?
     *
     * @return True - passed; False - failed.
     */
    Boolean isPassed();
}
