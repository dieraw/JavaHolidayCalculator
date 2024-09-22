package com.holidaycalculator.controller;

import com.holidaycalculator.service.CalculatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/calculate")
    public double calculateVacationPay(
            @RequestParam double averageSalary,
            @RequestParam int vacationDays,
            @RequestParam(required = false) List<LocalDate> vacationDates) {

        // Если переданы конкретные даты, считаем отпускные с учетом рабочих дней
        if (vacationDates != null && !vacationDates.isEmpty()) {
            return calculatorService.calculateVacationPayWithDates(averageSalary, vacationDates);
        } else {
            // Если даты не указаны, считаем отпускные по количеству дней отпуска
            return calculatorService.calculateVacationPay(averageSalary, vacationDays);
        }
    }
}