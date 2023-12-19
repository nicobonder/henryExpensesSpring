package com.Henry.Expenses.service;

import com.Henry.Expenses.dto.Expense;
import com.Henry.Expenses.dto.request.ExpenseRequestDto;

import java.util.ArrayList;
import java.util.Optional;

public interface ExpenseService {
    String createExpense(ExpenseRequestDto expenseRequestDto);
    ArrayList<Expense> getAllExpenses();


    Optional<Expense> getExpenseById(Long id);
}
