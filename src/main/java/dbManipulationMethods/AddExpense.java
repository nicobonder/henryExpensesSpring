package dbManipulationMethods;

import Excepcions.InvalidAmountExcepcion;

@FunctionalInterface
public interface AddExpense {
    public void addExpense() throws InvalidAmountExcepcion;
}
