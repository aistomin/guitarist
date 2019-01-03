package com.github.aistomin.guitarist.advanced;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.github.aistomin.guitarist.simple.SimpleResult;
import org.junit.jupiter.api.Test;

/**
 * Created by aistomin on 2019-01-03.
 * <p>
 * The test for {@link PercentageBasedResult}
 */
class PercentageBasedResultTest {

    /**
     * Check that we correctly detect if the test is finished.
     */
    @Test
    void testIsFinished() {
        assertTrue(
            new PercentageBasedResult(new SimpleResult(2, 2, 1, 1), 60)
                .isFinished()
        );
        assertFalse(
            new PercentageBasedResult(new SimpleResult(3, 1, 1, 0), 50)
                .isFinished()
        );
    }

    /**
     * Check that we correctly detect if the test is passed.
     */
    @Test
    void testIsPassed() {
        assertFalse(
            new PercentageBasedResult(new SimpleResult(3, 2, 2, 0), 50)
                .isPassed()
        );
        assertFalse(
            new PercentageBasedResult(new SimpleResult(4, 4, 2, 2), 60)
                .isPassed()
        );
        assertTrue(
            new PercentageBasedResult(new SimpleResult(5, 5, 3, 2), 50)
                .isPassed()
        );
        assertTrue(
            new PercentageBasedResult(new SimpleResult(6, 6, 6, 0), 50)
                .isPassed()
        );
    }
}
