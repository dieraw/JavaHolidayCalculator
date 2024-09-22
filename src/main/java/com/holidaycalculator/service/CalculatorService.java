package com.holidaycalculator.service;

import com.holidaycalculator.utils.WorkingDaysCalculator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CalculatorService {

    // Базовый расчет отпускных на основе количества дней
    public double calculateVacationPay(double averageSalary, int vacationDays) {
        double dailySalary = averageSalary / 29.3; // базовый расчет
        return dailySalary * vacationDays;
    }

    // Новый метод для расчета отпускных с учетом дат ухода в отпуск
    public double calculateVacationPayWithDates(double averageSalary, List<LocalDate> vacationDates) {
        double dailySalary = averageSalary / 29.3;
        // Используем утилиту для подсчета рабочих дней (без праздников и выходных)
        int workingDays = WorkingDaysCalculator.countWorkingDays(vacationDates);
        return dailySalary * workingDays;
    }
}
