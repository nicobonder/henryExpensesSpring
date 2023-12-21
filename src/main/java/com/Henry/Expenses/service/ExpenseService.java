package com.Henry.Expenses.service;

import com.Henry.Expenses.dto.Expense;
import com.Henry.Expenses.dto.request.ExpenseRequestDto;

import java.util.List;
import java.util.Optional;

public interface ExpenseService {
    String createExpense(ExpenseRequestDto expenseRequestDto);
    String updateExpense(Long id, ExpenseRequestDto expenseRequestDto);

    List<Expense> getAllExpenses();


    Optional<Expense> getExpenseById(Long id);

    boolean deleteExpenseById(Long id);
}
