package com.holidaycalculator.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    public void testCalculateVacationPay() {
        double averageSalary = 60000;
        int vacationDays = 14;
        double expectedPay = 28669.97; // вручную рассчитанное значение

        double result = calculatorService.calculateVacationPay(averageSalary, vacationDays);
        assertEquals(expectedPay, result, 0.01); // проверка с точностью до 0.01
    }
}