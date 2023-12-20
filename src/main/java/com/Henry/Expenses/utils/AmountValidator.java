package com.Henry.Expenses.utils;

import com.Henry.Expenses.Excepcions.InvalidAmountException;

@FunctionalInterface
    public interface AmountValidator {
        boolean notValidAmount(double amount) throws InvalidAmountException;
    }

