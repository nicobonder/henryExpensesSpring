package com.Henry.Expenses.utils;

import com.Henry.Expenses.Excepcions.InvalidAmountExcepcion;

public class AmountValidatorImpl implements AmountValidator {
    @Override
    public boolean notValidAmount(double amount) throws InvalidAmountExcepcion {
        if(amount < 0) {
            throw  new InvalidAmountExcepcion("El monto debe ser igual o mayor a 0");
        }
        return false;
    }
}
