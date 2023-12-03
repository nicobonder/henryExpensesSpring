package com.Henry.Expenses;

import Excepcions.InvalidAmountExcepcion;
import dbManipulationMethods.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ExpensesApplication {

	public static void main(String[] args) throws InvalidAmountExcepcion {
		SpringApplication.run(ExpensesApplication.class, args);

		Scanner scanner = new Scanner(System.in);

		boolean exit = false;

		do {
			displayMenu();
			int option = scanner.nextInt();
			scanner.nextLine(); // Limpiar el buffer

			switch (option) {
				case 1:
					addExpenseToDB();
					break;
				case 2:
					ShowAllExpenesFromDB();
					break;
				case 3:
					ShowCategoryQantityUsed();
					break;
				case 4:
					FilterExpensesByAmount();
					break;
				case 5:
					FindExpenseById();
					break;
				case 6:
					UpdateExpense();
					break;
				case 7:
					DeleteExpense();
					break;
				case 8:
					ShowExpenesByDay();
					break;
				case 0:
					exit = true;
					break;
				default:
					System.out.println("Opción no válida. Intente de nuevo.");
			}

		} while (!exit);
	} //termina el método main

	public static void displayMenu() {
		System.out.println("-------- Menú --------");
		System.out.println("1 - Cargar un nuevo gasto");
		System.out.println("2 - Ver todos los gastos cargados");
		System.out.println("3 - Mostrar la cantidad de gastos por categoría");
		System.out.println("4 - Filtrar gastos por monto");
		System.out.println("5 - Ver detalles de un gasto");
		System.out.println("6 - Actualizar un gasto");
		System.out.println("7 - Borrar un gasto");
		System.out.println("8 - Mostrar gastos por día");
		System.out.println("0 - Salir");
		System.out.print("Ingrese la opción deseada: ");
	}

	//AGREGO EL GASTO DESDE EL METODO que está en AddExpenseImpl
	public static void addExpenseToDB() throws InvalidAmountExcepcion {
		AddExpense addExpense = new AddExpenseImpl();
		addExpense.addExpense();
	}

	public static void ShowAllExpenesFromDB() {
		ShowAllExpenses showAllExpenses = new ShowAllExpensesImpl();
		showAllExpenses.showAllExpenses();
	}

	public static void ShowCategoryQantityUsed() {
		CategoryQuantityUsed categoryQuantityUsed = new CategoryQuantityUsedImpl();
		categoryQuantityUsed.showCategoryQuantityUsed();
	}

	public static void FilterExpensesByAmount() {
		FilterExpensesBiggerThan filterExpenses = new FilterExpensesBiggerThanImpl();
		filterExpenses.filterExpensedBiggerThan();
	}

	public static void FindExpenseById() {
		GetExpenseByIdDB getExpenseById = new GetExpenseByIdDBImpl();
		getExpenseById.getExpenseByIdDB();
	}

	public static void UpdateExpense() {
		System.out.println();
		UpdateExpense updateExpense = new UpdateExpenseImpl();
		updateExpense.updateExpense();
	}

	public static void DeleteExpense() {
		System.out.println();
		DeleteExpense deleteExpense = new DeleteExpenseImpl();
		deleteExpense.deleteExpense();
	}

	public static void ShowExpenesByDay(){
		ShowExpensesByDay showExpensesByDay = new ShowExpensesByDayImpl();
		showExpensesByDay.showExpensesByDay();
	}
}

