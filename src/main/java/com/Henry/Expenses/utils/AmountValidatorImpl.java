package com.Henry.Expenses.utils;

import com.Henry.Expenses.Excepcions.InvalidAmountException;

public class AmountValidatorImpl implements AmountValidator {
    @Override
    public boolean notValidAmount(double amount) throws InvalidAmountException {
        if(amount < 0) {
            throw  new InvalidAmountException("El monto debe ser igual o mayor a 0");
        }
        return false;
    }
}
