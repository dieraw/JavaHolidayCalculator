package com.holidaycalculator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    public void testCalculateVacationPay() {
        double averageSalary = 60000;
        int vacationDays = 16;
        double expectedPay = 32764.51; // вручную рассчитанное значение

        double result = calculatorService.calculateVacationPay(averageSalary, vacationDays);
        assertEquals(expectedPay, result, 0.01);
    }

    // Тест с одним отпускным днем
    @Test
    public void testCalculateVacationPayWithOneDay() {
        double averageSalary = 60000;
        int vacationDays = 1;
        double expectedPay = 2047.78; // вручную рассчитанное значение

        double result = calculatorService.calculateVacationPay(averageSalary, vacationDays);
        assertEquals(expectedPay, result, 0.01);
    }

    // Тест с нулевым количеством отпускных дней
    @Test
    public void testCalculateVacationPayWithZeroDays() {
        double averageSalary = 60000;
        int vacationDays = 0;
        double expectedPay = 0.0; // при 0 днях отпускных

        double result = calculatorService.calculateVacationPay(averageSalary, vacationDays);
        assertEquals(expectedPay, result, 0.01);
    }

    // Тест с 30 отпускными днями
    @Test
    public void testCalculateVacationPayWithThirtyDays() {
        double averageSalary = 60000;
        int vacationDays = 30;
        double expectedPay = 61433.45; // вручную рассчитанное значение

        double result = calculatorService.calculateVacationPay(averageSalary, vacationDays);
        assertEquals(expectedPay, result, 0.01);
    }

    // Тест на расчет отпускных с использованием точных дат (без учета выходных и праздников)
    @Test
    public void testCalculateVacationPayWithDates() {
        double averageSalary = 60000;
        List<LocalDate> vacationDates = Arrays.asList(
                LocalDate.of(2024, 5, 1),  // Праздник (будет исключен)
                LocalDate.of(2024, 5, 2),  // Рабочий день
                LocalDate.of(2024, 5, 3),  // Рабочий день
                LocalDate.of(2024, 5, 4),  // Суббота (будет исключена)
                LocalDate.of(2024, 5, 5)   // Воскресенье (будет исключено)
        );
        double expectedPay = 4095.56; // расчет для 2 рабочих дней

        double result = calculatorService.calculateVacationPayWithDates(averageSalary, vacationDates);
        assertEquals(expectedPay, result, 0.01);
    }

    // Тест с отпуском, который выпадает только на выходные
    @Test
    public void testCalculateVacationPayWithOnlyWeekends() {
        double averageSalary = 60000;
        List<LocalDate> vacationDates = Arrays.asList(
                LocalDate.of(2024, 5, 4),  // Суббота
                LocalDate.of(2024, 5, 5)   // Воскресенье
        );
        double expectedPay = 0.0; // отпуск выпадает на выходные

        double result = calculatorService.calculateVacationPayWithDates(averageSalary, vacationDates);
        assertEquals(expectedPay, result, 0.01);
    }

    // Тест с отпуском, который выпадает на праздники
    @Test
    public void testCalculateVacationPayWithOnlyHolidays() {
        double averageSalary = 60000;
        List<LocalDate> vacationDates = Arrays.asList(
                LocalDate.of(2024, 1, 1),  // Новый год
                LocalDate.of(2024, 12, 25) // Рождество
        );
        double expectedPay = 0.0; // отпуск выпадает на праздники

        double result = calculatorService.calculateVacationPayWithDates(averageSalary, vacationDates);
        assertEquals(expectedPay, result, 0.01);
    }
}
