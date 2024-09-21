package com.holidaycalculator.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class WorkingDaysCalculator {

    // Список праздничных дней (можно расширить для поддержки динамических праздников)
    private static final Set<LocalDate> HOLIDAYS = Set.of(
            LocalDate.of(2024, 1, 1), // Новый год
            LocalDate.of(2024, 5, 1)  // День труда
            // Добавьте сюда другие праздничные дни
    );

    public static int countWorkingDays(List<LocalDate> vacationDates) {
        int workingDays = 0;

        for (LocalDate date : vacationDates) {
            if (isWorkingDay(date)) {
                workingDays++;
            }
        }

        return workingDays;
    }

    // Проверка, является ли день рабочим
    private static boolean isWorkingDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        // День не является рабочим, если это суббота, воскресенье или праздничный день
        return dayOfWeek != DayOfWeek.SATURDAY &&
                dayOfWeek != DayOfWeek.SUNDAY &&
                !HOLIDAYS.contains(date);
    }
}
