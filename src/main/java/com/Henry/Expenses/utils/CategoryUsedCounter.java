package com.Henry.Expenses.utils;

import com.Henry.Expenses.dto.Expense;

import java.util.List;

@FunctionalInterface
public interface CategoryUsedCounter {
    void countCategoryUsed(List<Expense> expenses);
}