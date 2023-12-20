package com.Henry.Expenses.service;

import com.Henry.Expenses.domain.categories.ExpenseCategory;
import com.Henry.Expenses.dto.Expense;
import com.Henry.Expenses.dto.request.ExpenseRequestDto;
import org.springframework.stereotype.Service;
import com.Henry.Expenses.repository.ExpenseRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    //Inyectamos el repo para poder usar sus metodos
    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public String createExpense(ExpenseRequestDto expenseRequestDto) {
        String response = "El gasto se registró con éxito";
        Expense expense = mapDtoToExpense(expenseRequestDto);

        Integer responseInserted = expenseRepository.insert(expense);
        if(responseInserted.equals(0)){
            System.out.println("No se insertó ningún registro");
        }

        return response;
    }

    @Override
    public String updateExpense(Long id, ExpenseRequestDto expenseRequestDto) {
        String response = "El gasto se actualizó con éxito";

        Expense expense = mapDtoToExpense(expenseRequestDto);
        Integer responseUpdated = expenseRepository.update(id, expense);

        // Si el update de BD no devolvió ningún registro actualizado, entonces devuelvo un mensaje de error
        if (responseUpdated.equals(0)) {
            System.out.println("No se actualizó ningún registro con el id " + id);
        }
        System.out.println("Se actualizó el gasto id: " + id);
        return response;
    }


    @Override
    public ArrayList<Expense> getAllExpenses() {
        ArrayList<Expense> expenses = expenseRepository.getAll();
        if(expenses.size() > 0){
            return expenses;
        }
        System.out.println("No se encontraron gastos");
        return null;
    }

    @Override
    public Optional<Expense> getExpenseById(Long id) {
        Expense expense = expenseRepository.getById(id);

        return Optional.of(expense);
    }

    @Override
    public boolean deleteExpenseById(Long id) {
        try {
            expenseRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    private Expense mapDtoToExpense(ExpenseRequestDto expenseRequestDto){
        Expense expense = new Expense();
        expense.setAmount(expenseRequestDto.getAmount());
        expense.setCategoryName(expenseRequestDto.getCategoryRequestDto().getName());
        expense.setDate(expenseRequestDto.getDate());
        return expense;
    }

}
