package com.Henry.Expenses.domain.expenses;

import com.Henry.Expenses.dto.Expense;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Clase para mostrar el total gastado por día
//Seria una tabla con 2 columnas: dia y monto
public class DailyExpenses {
    public static void addDailyExpenses(List<com.Henry.Expenses.dto.Expense> expensesList) {
        Map<String, Double> gastosDiarios = new HashMap<>();

        for (Expense g : expensesList) {
            String fecha = g.getDate();
            double monto = g.getAmount();

            if (gastosDiarios.containsKey(fecha)) { //devuelve true si la key existe
                double montoExistente = gastosDiarios.get(fecha);  //el get de un map devuelve el valor asociado a la key
                gastosDiarios.put(fecha, montoExistente + monto); //put es el metodo para agregar en un hashmap
            } else {
                gastosDiarios.put(fecha, monto);
            }
        }

        for (String fecha : gastosDiarios.keySet()) {
            double montoTotal = gastosDiarios.get(fecha);
            System.out.println("Fecha: " + fecha + " - Monto Total: " + montoTotal);
        }
    }

}
