package com.holidaycalculator.controller;

import com.holidaycalculator.service.CalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
public class CalculatorController {

    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<?> calculateVacationPay(
            @RequestParam(required = false) Double averageSalary,
            @RequestParam(required = false) Integer vacationDays,
            @RequestParam(required = false) String vacationDates) {

        logger.info("Received request with averageSalary={}, vacationDays={}, vacationDates={}",
                averageSalary, vacationDays, vacationDates);

        try {
            if (averageSalary == null) {
                return ResponseEntity.badRequest().body("averageSalary is required");
            }

            if (vacationDates != null && !vacationDates.isEmpty()) {
                List<LocalDate> dates = Arrays.stream(vacationDates.split(","))
                        .map(date -> {
                            logger.info("Parsing date: {}", date);
                            return LocalDate.parse(date.trim());
                        })
                        .collect(Collectors.toList());
                logger.info("Parsed dates: {}", dates);
                double result = calculatorService.calculateVacationPayWithDates(averageSalary, dates);
                return ResponseEntity.ok(result);
            } else if (vacationDays != null) {
                double result = calculatorService.calculateVacationPay(averageSalary, vacationDays);
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.badRequest().body("Either vacationDays or vacationDates must be provided");
            }
        } catch (Exception e) {
            logger.error("Error processing request", e);
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}