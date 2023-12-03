package dbManipulationMethods;

import config.ConnectionDB;
import repository.ExpenseRepository;
import repository.ExpenseRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteExpenseImpl implements DeleteExpense {
    public void deleteExpense(){
        try (Connection connection = ConnectionDB.getConnection()) {
            ExpenseRepository expenseDao = new ExpenseRepositoryImpl(connection);


                System.out.println("Ingresa el id del gasto que quieres borrar: ");
                Scanner teclado1 = new Scanner(System.in);
                int id = teclado1.nextInt();
                expenseDao.deleteExpense(id);
                System.out.println("Gasto borrado con éxito");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
