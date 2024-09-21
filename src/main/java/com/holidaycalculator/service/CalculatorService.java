package com.holidaycalculator.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public double calculateVacationPay(double averageSalary, int vacationDays) {
        // Рассчитываем дневную зарплату (предположим, 29.3 дней в месяце)
        double dailySalary = averageSalary / 29.3;
        // Считаем сумму отпускных за количество дней отпуска
        return dailySalary * vacationDays;
    }
}
