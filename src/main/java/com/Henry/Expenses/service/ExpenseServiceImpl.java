package com.Henry.Expenses.service;

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

    private Expense mapDtoToExpense(ExpenseRequestDto expenseRequestDto){
        Expense expense = new Expense();
        expense.setAmount(expenseRequestDto.getAmount());
        expense.setCategoryName(expenseRequestDto.getCategoryRequestDto().getName());
        expense.setDate(expenseRequestDto.getDate());
        return expense;
    }

}