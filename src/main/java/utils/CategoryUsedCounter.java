package utils;

import dto.Expense;

import java.util.List;

@FunctionalInterface
public interface CategoryUsedCounter {
    void countCategoryUsed(List<Expense> expenses);
}