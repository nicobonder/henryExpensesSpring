package utils;

import dto.ExpenseDto;

import java.util.List;

@FunctionalInterface
public interface CategoryUsedCounter {
    void countCategoryUsed(List<ExpenseDto> expenses);
}