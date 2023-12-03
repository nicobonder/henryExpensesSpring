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

public class UpdateExpenseImpl implements UpdateExpense {
    @Override
    public void updateExpense() {
        try (Connection connection = ConnectionDB.getConnection()) {
            //TRABAJO CON LOS METODOS DE EXPENSE DAO, ENTONCES LO INSTANCIA
            ExpenseRepository expenseDao = new ExpenseRepositoryImpl(connection);
            ExpenseCategory category = new ExpenseCategory();

                System.out.println("Ingresa el id del gasto que quieres actualizar: ");
                Scanner teclado1 = new Scanner(System.in);
                int id = teclado1.nextInt();
                ExpenseDto expenseById = expenseDao.getExpenseById(id);

                //Importamos la interfaz de validacion
                AmountValidator amountValidator = new AmountValidatorImpl();

                //VOLVER A PEDIR LOS DETALLES DEL GASTO Y SETEAR expenseById
                System.out.print("Ingresa la fecha del gasto en formato DD/MM/AA: ");
                Scanner teclado2 = new Scanner(System.in);
                String date = teclado2.nextLine();

                System.out.print("Ingresa el monto del gasto: ");
                Scanner tecladoAmount = new Scanner(System.in);
                double amount = tecladoAmount.nextDouble();

                //Valido el monto con el validador
                if (!amountValidator.notValidAmount(amount)) {
                    System.out.println("El monto es valido");
                }

                System.out.println("Selecciona la categoría del gasto. Ingresa:");
                System.out.println("1 si fue de Alimentación\n2 si fue de Educación\n3 si fue de Salud \n4 si fue de Transporte \n5 si fue de Vestimenta\n6 si fue de Esparcimiento\n7 si fue de Deporte \n8 si fue de otra categoría");
                Scanner teclado3 = new Scanner(System.in);
                int categoryUser = teclado3.nextInt();
                List<ExpenseCategory> categories = ExpenseCategories.getExpenseCategories();
                if (categoryUser >= 1 && categoryUser <= categories.size()) {
                    int categoryId = categories.get(categoryUser - 1).getId(); // Obtén el ID de la categoría
                    category.setId(categoryId);

                    expenseById.setDate(date);
                    expenseById.setAmount(amount);
                    expenseById.setCategory(category);
                    expenseById.setId(id);

                    //Actualizo el gasto en la DB
                    expenseDao.updateExpense(expenseById);

                    //Registro el gasto en la DB
                   // expenseDao.addExpense(expenseDto);
                    System.out.println("Gasto modificado con éxito.");

                } else {
                    System.out.println("Opción de categoría no válida.");
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InvalidAmountExcepcion e) {
            throw new RuntimeException(e);
        }
    }
}