package com.Henry.Expenses.service;

import com.Henry.Expenses.Excepcions.InvalidAmountException;
import com.Henry.Expenses.dto.Expense;
import com.Henry.Expenses.dto.request.ExpenseRequestDto;
import org.springframework.stereotype.Service;
import com.Henry.Expenses.repository.ExpenseRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        try {
            validateAmount(expenseRequestDto.getAmount());
        String response = "El gasto se registró con éxito";
        Expense expense = mapDtoToExpense(expenseRequestDto);

        Integer responseInserted = expenseRepository.insert(expense);
        if(responseInserted.equals(0)){
            System.out.println("No se insertó ningún registro");
        }

        return response;
        }catch (InvalidAmountException e){
            return "Error: " +  e.getMessage();
        }
    }

    public void validateAmount(Double amount) throws InvalidAmountException {
        // Lógica de validación del monto
        if (amount == null || amount <= 0) {
            throw new InvalidAmountException("El monto debe ser un valor positivo.");
        }
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
    public List<Expense> getAllExpenses() {
        List<Expense> expenses = expenseRepository.getAll();
        return expenses != null ? expenses : Collections.emptyList();
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
