package com.Henry.Expenses.domain.categories;

import java.util.ArrayList;
import java.util.List;

public class ExpenseCategories {
    private static List<ExpenseCategory> ExpenseCategories = new ArrayList<>();

    static {
        ExpenseCategories.add(new ExpenseCategory(1L, "Alimentacion"));
        ExpenseCategories.add(new ExpenseCategory(2L, "Educacion"));
        ExpenseCategories.add(new ExpenseCategory(3L, "Salud"));
        ExpenseCategories.add(new ExpenseCategory(4L, "Transporte"));
        ExpenseCategories.add(new ExpenseCategory(5L, "Vestimenta"));
        ExpenseCategories.add(new ExpenseCategory(6L, "Esparcimiento"));
        ExpenseCategories.add(new ExpenseCategory(7L, "Deporte"));
        ExpenseCategories.add(new ExpenseCategory(8L, "Otros"));
    }

    public static List<ExpenseCategory> getExpenseCategories() {
        return ExpenseCategories;
    }

    public int size() {
        return ExpenseCategories.size();
    }
}
