package com.Henry.Expenses.domain.expenses;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Expenses {
    private List<Expense> ExpensesList;

    public Expenses() {
        this.ExpensesList = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        ExpensesList.add(expense);
    }

    public List<Expense> getExpensesList() {
        return ExpensesList;
    }


}