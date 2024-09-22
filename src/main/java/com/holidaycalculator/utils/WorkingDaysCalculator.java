package com.holidaycalculator.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class WorkingDaysCalculator {

    // Список фиксированных праздников (можно расширить или подгружать из базы данных)
    private static final Set<LocalDate> HOLIDAYS = Set.of(
            LocalDate.of(2024, 1, 1), // Новый год
            LocalDate.of(2024, 5, 1), // День труда
            LocalDate.of(2024, 12, 25) // Рождество
            // Добавляйте другие праздники по мере необходимости
    );

    // Метод для подсчета рабочих дней в списке отпускных дат
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
