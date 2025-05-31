package com.solo.gilded.rose.util;

import com.solo.gilded.rose.entity.Operation;
import org.junit.jupiter.api.Test;

import static com.solo.gilded.rose.util.StateCalculatorUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class StateCalculatorUtilTest {

    @Test
    void testIncreaseWithZero() {
        int result = calculate(Operation.INCREASE, 0, 100);
        assertEquals(100, result);
    }

    @Test
    void testDecreaseWithZero() {
        int result = calculate(Operation.DECREASE, 100, 0);
        assertEquals(100, result);
    }

    @Test
    void testIncreaseOperation() {
        int result = calculate(Operation.INCREASE, 10, 5);
        assertEquals(15, result);
    }

    @Test
    void testDecreaseOperation() {
        int result = calculate(Operation.DECREASE, 10, 5);
        assertEquals(5, result);
    }

    @Test
    void testUnsupportedOperationThrowsException() {
        UnsupportedOperationException exception = assertThrows(
                UnsupportedOperationException.class,
                () -> calculate(Operation.OTHER, 10, 5)
        );
        assertEquals("Only Increase and decrease is supported", exception.getMessage());
    }

}