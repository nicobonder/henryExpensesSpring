package service;

import dto.Expense;
import dto.request.ExpenseRequestDto;
import org.springframework.stereotype.Service;
import repository.ExpenseRepository;

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

    private dto.Expense mapDtoToExpense(ExpenseRequestDto expenseRequestDto){
        dto.Expense expense = new dto.Expense();
        expense.setAmount(expenseRequestDto.getAmount());
        expense.setCategoryName(expenseRequestDto.getCategoryRequestDto().getName());
        expense.setDate(expenseRequestDto.getDate());
        return expense;
    }
}
