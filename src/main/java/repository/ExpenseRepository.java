package repository;

import Excepcions.RepositoryExepcion;
import domain.categories.ExpenseCategory;
import dto.Expense;

import java.sql.SQLException;
import java.util.List;

public interface ExpenseRepository {
        Integer insert(Expense expense);

/*        ExpenseCategory getExpenseCategoryById(int id) throws SQLException;
        Expense getExpenseById(int id);

        List<Expense> getAllExpenses() throws RepositoryExepcion;

        void updateExpense(Expense expense);

        void deleteExpense(int id);*/
    }


