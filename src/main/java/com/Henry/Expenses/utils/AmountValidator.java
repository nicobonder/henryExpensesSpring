package com.Henry.Expenses.utils;

import com.Henry.Expenses.Excepcions.InvalidAmountExcepcion;

@FunctionalInterface
    public interface AmountValidator {
        boolean notValidAmount(double amount) throws InvalidAmountExcepcion;
    }

