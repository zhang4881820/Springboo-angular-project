package com.zhang.myproject.springTest;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * create by zhangbo on 2019/11/6 0006
 */
public class SimpleCalculatorTests {

    private SimpleCalculator simpleCalculator;

    @Before
    public void setup() {
        simpleCalculator = new SimpleCalculator();
    }

    @Test
    public void verifyAdd() {
        long sum = simpleCalculator.addOperation(2, 1);
        Assert.assertEquals(3, sum);
    }

    @After
    public void teardown() {
        simpleCalculator = null;
    }
}
