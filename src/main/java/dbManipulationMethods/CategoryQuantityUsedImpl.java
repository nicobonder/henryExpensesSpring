package dbManipulationMethods;

import Excepcions.RepositoryExepcion;
import config.ConnectionDB;
import dto.ExpenseDto;
import repository.ExpenseRepository;
import repository.ExpenseRepositoryImpl;
import utils.CategoryUsedCounterImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoryQuantityUsedImpl implements CategoryQuantityUsed {
    @Override
    public void showCategoryQuantityUsed() {

        try (Connection connection = ConnectionDB.getConnection()) {
            //Metodo para hacer un map que registre la cantidad de veces que aparece cada categoria
            ExpenseRepository expenseDao = new ExpenseRepositoryImpl(connection);
            List<ExpenseDto> expenses = expenseDao.getAllExpenses();

            CategoryUsedCounterImpl categoryCounter = new CategoryUsedCounterImpl();
            categoryCounter.countCategoryUsed(expenses);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RepositoryExepcion e) {
            throw new RuntimeException(e);
        }

    }
}
