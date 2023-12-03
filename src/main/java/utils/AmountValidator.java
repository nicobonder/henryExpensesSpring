package utils;

import Excepcions.InvalidAmountExcepcion;

@FunctionalInterface
    public interface AmountValidator {
        boolean notValidAmount(double amount) throws InvalidAmountExcepcion;
    }

