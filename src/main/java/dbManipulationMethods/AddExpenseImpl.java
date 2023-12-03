package dbManipulationMethods;
import Excepcions.InvalidAmountExcepcion;
import config.ConnectionDB;
import domain.categories.ExpenseCategories;
import domain.categories.ExpenseCategory;
import dto.ExpenseDto;
import repository.ExpenseRepository;
import repository.ExpenseRepositoryImpl;
import utils.AmountValidator;
import utils.AmountValidatorImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AddExpenseImpl implements AddExpense {


    @Override
    public void addExpense() throws InvalidAmountExcepcion {
        try (Connection connection = ConnectionDB.getConnection()) {
            ExpenseRepository expenseDao = new ExpenseRepositoryImpl(connection);
            ExpenseDto expenseDto = new ExpenseDto();
            ExpenseCategory category = new ExpenseCategory();

            System.out.print("Ingresa la fecha del gasto en formato DD/MM/AA: ");
            Scanner teclado = new Scanner(System.in);
            String date = teclado.nextLine();

            System.out.print("Ingresa el monto del gasto: ");
            Scanner teclado2 = new Scanner(System.in);
            double amount = teclado2.nextDouble();
            //Valido el monto con el validador
            AmountValidator amountValidator = new AmountValidatorImpl();
            if (!amountValidator.notValidAmount(amount)) {
                System.out.println("El monto es valido");
            }

            System.out.println("Selecciona la categoría del gasto. Ingresa:");
            System.out.println("1 si fue de Alimentación\n2 si fue de Educación\n3 si fue de Salud \n4 si fue de Transporte \n5 si fue de Vestimenta\n6 si fue de Esparcimiento\n7 si fue de Deporte \n8 si fue de otra categoría");
            Scanner teclado3 = new Scanner(System.in);
            int categoryUser = teclado3.nextInt();
            List<ExpenseCategory> categories = ExpenseCategories.getExpenseCategories();
            if (categoryUser >= 1 && categoryUser <= categories.size()) {
                int categoryId = categories.get(categoryUser - 1).getId();
                category.setId(categoryId);

                expenseDto.setDate(date);
                expenseDto.setAmount(amount);
                expenseDto.setCategory(category);
                expenseDao.addExpense(expenseDto);
                System.out.println("Gasto agregado con éxito.");
            } else {
                System.out.println("Opción de categoría no válida.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

