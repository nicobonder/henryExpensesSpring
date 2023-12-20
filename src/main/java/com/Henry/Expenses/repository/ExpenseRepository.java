package com.Henry.Expenses.repository;

import com.Henry.Expenses.dto.Expense;

import java.util.ArrayList;

public interface ExpenseRepository {
        Integer insert(Expense expense);
        Integer update(Long id, Expense expense);

        ArrayList<Expense> getAll();

        Expense getById(Long id);

    void deleteById(Long id);

/*        ExpenseCategory getExpenseCategoryById(int id) throws SQLException;
        Expense getExpenseById(int id);

        List<Expense> getAllExpenses() throws RepositoryExepcion;

        void updateExpense(Expense expense);

        void deleteExpense(int id);*/
    }


