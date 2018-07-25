package com.github.aistomin.guitarist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by aistomin on 25.07.18.
 */
class DummyTest {

    @Test
    void test() {
        Assertions.assertEquals(4, new Dummy().value());
    }
}