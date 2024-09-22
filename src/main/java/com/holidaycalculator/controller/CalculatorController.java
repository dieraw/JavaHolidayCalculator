package com.holidaycalculator.controller;

import com.holidaycalculator.service.CalculatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

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
            @RequestParam(required = false) String vacationDates) {

        // Проверяем, есть ли даты в виде строки
        if (vacationDates != null && !vacationDates.isEmpty()) {
            // Разбиваем строку с датами на список LocalDate
            List<LocalDate> dates = Arrays.stream(vacationDates.split(","))
                    .map(LocalDate::parse)
                    .collect(Collectors.toList());
            // Вызываем метод расчета по датам
            return calculatorService.calculateVacationPayWithDates(averageSalary, dates);
        } else {
            // Если даты не указаны, выполняем стандартный расчет
            return calculatorService.calculateVacationPay(averageSalary, vacationDays);
        }
    }
}