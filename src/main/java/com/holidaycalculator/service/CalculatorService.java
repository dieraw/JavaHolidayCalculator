package com.holidaycalculator.service;

import com.holidaycalculator.utils.WorkingDaysCalculator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CalculatorService {

    // Метод для расчета отпускных на основе количества отпускных дней
    public double calculateVacationPay(double averageSalary, int vacationDays) {
        double dailySalary = averageSalary / 29.3; // базовый расчет
        return dailySalary * vacationDays;
    }

    // Новый метод для расчета отпускных на основе конкретных дней отпуска
    public double calculateVacationPayWithDates(double averageSalary, List<LocalDate> vacationDates) {
        double dailySalary = averageSalary / 29.3;
        // Используем утилиту для расчета количества рабочих дней
        int workingDays = WorkingDaysCalculator.countWorkingDays(vacationDates);
        return dailySalary * workingDays;
    }
}