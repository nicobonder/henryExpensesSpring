package com.Henry.Expenses.Controller;

import com.Henry.Expenses.dto.Expense;
import com.Henry.Expenses.dto.request.ExpenseRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Henry.Expenses.service.ExpenseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<String> createExpenseHandler(@RequestBody ExpenseRequestDto expenseRequestDto) {
        String response = expenseService.createExpense(expenseRequestDto);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);
    }

    //Actualizar un gasto
    @PutMapping(path = "update/{id}")
    public ResponseEntity<String> updateExpenseHandler(@PathVariable("id") Long id, @RequestBody ExpenseRequestDto expenseRequestDto) {
        String response = expenseService.updateExpense(id, expenseRequestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    //Get all expenses
    @GetMapping
    public List<Expense> getAllExpensesHandler() {
        return expenseService.getAllExpenses();
    }

    //Get expense by id
    @GetMapping(path = "/{id}")
    public Optional<Expense> getExpenseByIdHandler(@PathVariable("id") Long id) {
        return this.expenseService.getExpenseById(id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteExpenseByIdHandler(@PathVariable("id") Long id) {
        boolean ok = this.expenseService.deleteExpenseById(id);

        if (ok) {
            return "Se borró el gasto" + id;
        } else {
            return "No se pudo borrar el gasto" + id;
        }
    }


}
