package service;

import dto.request.ExpenseRequestDto;

public interface ExpenseService {
    String createExpense(ExpenseRequestDto expenseRequestDto);
}
