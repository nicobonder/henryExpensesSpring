package com.Henry.Expenses.service;

import com.Henry.Expenses.dto.Expense;
import com.Henry.Expenses.dto.request.ExpenseCategoryRequestDto;
import com.Henry.Expenses.dto.request.ExpenseRequestDto;
import com.Henry.Expenses.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpenseServiceImplTest {

    @InjectMocks
    ExpenseServiceImpl expenseService;

    @Mock
    ExpenseRepository expenseRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

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

    @Test
    @DisplayName("Prueba que al crear un gasto con monto válido se registra con éxito")
    void createExpense_Successful() {
        // Configuración del mock para devolver un valor de inserción exitoso
        when(expenseRepository.insert(any())).thenReturn(1);

        // Llamada al método que estás probando
        ExpenseRequestDto expenseRequestDto = new ExpenseRequestDto();
        expenseRequestDto.setAmount(50.00);
        expenseRequestDto.setDate("29/12/23");
        expenseRequestDto.setCategoryRequestDto(new ExpenseCategoryRequestDto());
        expenseRequestDto.getCategoryRequestDto().setName("Comida");

        String actualResponse = expenseService.createExpense(expenseRequestDto);

        // Verificación
        assertEquals("El gasto se registró con éxito", actualResponse);

        // Verificación de que el método insert del repositorio se llamó una vez
        verify(expenseRepository, times(1)).insert(any());
    }

    @Test
    @DisplayName("Prueba que al crear un gasto con monto inválido se maneja la excepción correctamente")
    void createExpense_InvalidAmount() {
        // Configuración del mock para devolver un valor de inserción exitoso
        when(expenseRepository.insert(any())).thenReturn(1);

        // Llamada al método que estás probando con un monto inválido
        ExpenseRequestDto expenseRequestDto = new ExpenseRequestDto();
        expenseRequestDto.setAmount(-10.00);

        String actualResponse = expenseService.createExpense(expenseRequestDto);

        // Verificación
        assertEquals("Error: El monto debe ser un valor positivo.", actualResponse);

        // Verificación de que el método insert del repositorio no se llamó
        verify(expenseRepository, never()).insert(any());
    }

}