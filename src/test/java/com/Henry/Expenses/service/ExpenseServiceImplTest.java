package com.Henry.Expenses.service;

import com.Henry.Expenses.Excepcions.InvalidAmountException;
import com.Henry.Expenses.dto.Expense;
import com.Henry.Expenses.dto.request.ExpenseCategoryRequestDto;
import com.Henry.Expenses.dto.request.ExpenseRequestDto;
import com.Henry.Expenses.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
class ExpenseServiceImplTest {

    @InjectMocks
    ExpenseServiceImpl expenseService;

    @Mock
    ExpenseRepository expenseRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
/*
    @Test
    @DisplayName("Prueba que al recibir los valores de un gasto, este se crea y se guarda en la DB")
    void createExpense_Successful() throws InvalidAmountException {
        ExpenseCategoryRequestDto categoryRequestDto = new ExpenseCategoryRequestDto();
        categoryRequestDto.setId(1L);
        categoryRequestDto.setName("Comida");


        ExpenseRequestDto gasto1 = new ExpenseRequestDto();
        gasto1.setAmount(25.00);
        gasto1.setCategoryRequestDto(categoryRequestDto);
        gasto1.setDate("29/12/23");

        String expectedResponse = "El gasto se registró con éxito";

        // Configuración del comportamiento de validación de cantidad
        doNothing().when(expenseService).validateAmount(anyDouble());

        // Llamada al método que estás probando
        String actualResponse = expenseService.createExpense(gasto1);

        // Verificación
        assertEquals(expectedResponse, actualResponse);

        // Verificación de que el método insert del repositorio se llamó una vez
        verify(expenseRepository, times(1)).insert(any(Expense.class));
    }

    @Test
    @DisplayName("Prueba que al recibir una cantidad inválida, se devuelve un mensaje de error")
    void createExpense_InvalidAmount() {
        // Configuración de la solicitud de gasto con una cantidad inválida
        ExpenseRequestDto expenseRequestDto = new ExpenseRequestDto();
        expenseRequestDto.setAmount(25.00); // Probar con numeros positivos y negativos para verificar si detecta una cantidad inválida

        // Configuración del mensaje de error esperado
        String expectedErrorMessage = "Error: La cantidad debe ser un número positivo";

        // Llamada al método que estás probando
        String actualResponse = expenseService.createExpense(expenseRequestDto);

        // Verificación
        assertEquals(expectedErrorMessage, actualResponse);

        // Verificación de que el método insert del repositorio no se llamó
        verify(expenseRepository, never()).insert(any(Expense.class));
    }*/

    @Test
    @DisplayName("Prueba que al llamar al método se muestran todos los gastos cargados en la DB")
    void getAllExpenses_Successfully() {
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(250.00, 1L , "Comida", "19/12/23"));
        expenses.add(new Expense(50.00, 2L , "Transporte", "22/12/23"));
        expenses.add(new Expense(75.00, 4L , "Salud", "20/12/23"));
        when(expenseRepository.getAll()).thenReturn((ArrayList<Expense>) expenses);

        List<Expense> result = expenseService.getAllExpenses();

        // Verificación
        assertNotNull(result);
        assertEquals(3, result.size(), "La cantidad de gastos es incorrecta");
        assertEquals(expenses, result, "Las listas de gastos no son iguales");

        // Verificación de que el método getAll del repositorio se llamó una vez
        verify(expenseRepository, times(1)).getAll();
    }
}