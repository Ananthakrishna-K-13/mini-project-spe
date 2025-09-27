package com.example.calculator;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    private static final double DELTA = 1e-15;

    @Test
    public void testSqrt() {
        assertEquals("Square root of 25 should be 5", 5.0, Calculator.sqrt(25), DELTA);
        assertEquals("Square root of 0 should be 0", 0.0, Calculator.sqrt(0), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSqrtNegative() {
        Calculator.sqrt(-1);
    }

    @Test
    public void testFactorial() {
        assertEquals("Factorial of 5 should be 120", 120.0, Calculator.factorial(5), DELTA);
        assertEquals("Factorial of 0 should be 1", 1.0, Calculator.factorial(0), DELTA);
        assertEquals("Factorial of 1 should be 1", 1.0, Calculator.factorial(1), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorialNegative() {
        Calculator.factorial(-5);
    }

    @Test
    public void testNaturalLog() {
        assertEquals("Natural log of 1 should be 0", 0.0, Calculator.naturalLog(1), DELTA);
        assertEquals("Natural log of e should be 1", 1.0, Calculator.naturalLog(Math.E), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNaturalLogZero() {
        Calculator.naturalLog(0);
    }

    @Test
    public void testPower() {
        assertEquals("2 to the power of 3 should be 8", 8.0, Calculator.power(2, 3), DELTA);
        assertEquals("5 to the power of 0 should be 1", 1.0, Calculator.power(5, 0), DELTA);
        assertEquals("4 to the power of 0.5 should be 2", 2.0, Calculator.power(4, 0.5), DELTA);
    }
}
