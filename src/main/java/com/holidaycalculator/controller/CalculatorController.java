package com.holidaycalculator.controller;

import com.holidaycalculator.model.CalculationResponse;
import com.holidaycalculator.service.CalculatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/calculate")
    public CalculationResponse calculateHolidayPayment(
            @RequestParam("averageSalary") double averageSalary,
            @RequestParam("vacationDays") int vacationDays) {
        double totalPayment = calculatorService.calculateVacationPay(averageSalary, vacationDays);
        return new CalculationResponse(totalPayment);
    }
}
