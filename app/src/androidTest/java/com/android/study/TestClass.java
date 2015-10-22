package com.android.study;

import android.test.InstrumentationTestCase;

/**
 * Created by user on 2015/10/21.
 */
public class TestClass extends InstrumentationTestCase {
    public void test() throws Exception {
        final int expected = 1;
        final int reality = 1;
        assertEquals(expected, reality);
    }
}
