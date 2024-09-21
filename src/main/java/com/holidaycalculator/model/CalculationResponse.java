package com.holidaycalculator.model;

public class CalculationResponse {

    private double vacationPay;

    public CalculationResponse(double vacationPay) {
        this.vacationPay = vacationPay;
    }

    public double getVacationPay() {
        return vacationPay;
    }

    public void setVacationPay(double vacationPay) {
        this.vacationPay = vacationPay;
    }
}
