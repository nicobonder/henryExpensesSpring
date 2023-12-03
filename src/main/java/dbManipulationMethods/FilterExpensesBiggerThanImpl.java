package dbManipulationMethods;

import Excepcions.RepositoryExepcion;
import config.ConnectionDB;
import dto.ExpenseDto;
import repository.ExpenseRepository;
import repository.ExpenseRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class FilterExpensesBiggerThanImpl implements FilterExpensesBiggerThan {
    @Override
    public void filterExpensedBiggerThan() {
    //Metodo para filtrar gastos mayores a $XX
        try (Connection connection = ConnectionDB.getConnection()) {
            System.out.println("Si quieres revisar tus mayores gastos, puedes filtrarlos ingresando el monto desde el cuál quieres filtrarlos.");

            System.out.println("Elige el monto mínimo para filtrar los gastos");
            Scanner teclado5 = new Scanner(System.in);
            double limitAmount = teclado5.nextDouble();
            ExpenseRepository expenseDao = new ExpenseRepositoryImpl(connection);
            List<ExpenseDto> expenses = expenseDao.getAllExpenses();

            List<ExpenseDto> filteredExpenses = expenses.stream()
                    .filter(expense -> expense.getAmount() > limitAmount)
                    .toList();
            System.out.println("Los gastos mayores a $" + limitAmount + " son:");
            filteredExpenses
                    .forEach(expense -> System.out.println(expense.getDate() + " - " + expense.getAmount() + " - " + expense.getCategory().getCategoryName()));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RepositoryExepcion e) {
            throw new RuntimeException(e);
        }
    }
}
