package dbManipulationMethods;

import config.ConnectionDB;
import dto.ExpenseDto;
import repository.ExpenseRepository;
import repository.ExpenseRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class GetExpenseByIdDBImpl implements GetExpenseByIdDB {
    @Override
    public void getExpenseByIdDB() {
            try (Connection connection = ConnectionDB.getConnection()) {
            //obtener gasto por id
            System.out.println();

            ExpenseRepository expenseDao = new ExpenseRepositoryImpl(connection);

                System.out.println("Ingresa el id del gasto que quieres ver: ");
                Scanner teclado7 = new Scanner(System.in);
                int id = teclado7.nextInt();
                ExpenseDto expenseById = expenseDao.getExpenseById(id);
                System.out.println("Date:" + expenseById.getDate() + " Amount:" + expenseById.getAmount() + " Category:" + expenseById.getCategory());

        } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}
