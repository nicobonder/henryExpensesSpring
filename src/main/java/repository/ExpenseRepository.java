package repository;

import Excepcions.RepositoryExepcion;
import domain.categories.ExpenseCategory;
import dto.ExpenseDto;

import java.sql.SQLException;
import java.util.List;

public interface ExpenseRepository {

        ExpenseCategory getExpenseCategoryById(int id) throws SQLException;
        ExpenseDto getExpenseById(int id);
        List<ExpenseDto> getAllExpenses() throws RepositoryExepcion;

        void addExpense(ExpenseDto expenseDto);

        void updateExpense(ExpenseDto expenseDto);

        void deleteExpense(int id);
    }


