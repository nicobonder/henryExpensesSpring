package dbManipulationMethods;

import Excepcions.RepositoryExepcion;
import config.ConnectionDB;
import domain.expenses.DailyExpenses;
import dto.ExpenseDto;
import repository.ExpenseRepository;
import repository.ExpenseRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ShowExpensesByDayImpl implements ShowExpensesByDay {
    @Override
    public void showExpensesByDay() {
        try (Connection connection = ConnectionDB.getConnection()) {
            ExpenseRepository expenseDao = new ExpenseRepositoryImpl(connection);
            List<ExpenseDto> expenses = expenseDao.getAllExpenses();

            System.out.println();
            System.out.println("Los gastos diarios son:");
            DailyExpenses.addDailyExpenses(expenses);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RepositoryExepcion e) {
            throw new RuntimeException(e);
        }
    }
}
