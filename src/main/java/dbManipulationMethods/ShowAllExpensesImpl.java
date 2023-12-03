package dbManipulationMethods;

import Excepcions.RepositoryExepcion;
import config.ConnectionDB;
import dto.ExpenseDto;
import repository.ExpenseRepository;
import repository.ExpenseRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ShowAllExpensesImpl implements ShowAllExpenses {
    @Override
    public void showAllExpenses() {
        //***AQUI COMIENZAN LOS METODOS PARA MOSTRAR****
        System.out.println();
        System.out.println("Estos son tus gastos registrados");
        try (Connection connection = ConnectionDB.getConnection()) {
            ExpenseRepository expenseDao = new ExpenseRepositoryImpl(connection);

            List<ExpenseDto> expenses = expenseDao.getAllExpenses();
            for (ExpenseDto expense : expenses) {
                System.out.println(expense.getDate() + " - " + expense.getAmount() + " - " + expense.getCategory().getCategoryName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RepositoryExepcion e) {
            throw new RuntimeException(e);
        }
    }
}