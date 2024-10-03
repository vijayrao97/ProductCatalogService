package com.scaler.productcatalogservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    public void testAdd_OnTwoIntegers_RunSuccessfully() {
        //Arange
        Calculator calc = new Calculator();
        //Act
        int result = calc.add(4, 8);
        //Assert
        assert(result == 12);
        //assertEquals(12, result);
    }

    @Test
     public void testDivideByZero_ResultInArithmeticException() {
        //Arange
        Calculator calc = new Calculator();
        //Assert
        assertThrows(ArithmeticException.class, () -> calc.divide(1,0));
     }

}