package domain.categories;

import java.util.ArrayList;
import java.util.List;

public class ExpenseCategories {
    private static List<ExpenseCategory> ExpenseCategories = new ArrayList<>();

    static {
        ExpenseCategories.add(new ExpenseCategory(1, "Alimentacion"));
        ExpenseCategories.add(new ExpenseCategory(2, "Educacion"));
        ExpenseCategories.add(new ExpenseCategory(3, "Salud"));
        ExpenseCategories.add(new ExpenseCategory(4, "Transporte"));
        ExpenseCategories.add(new ExpenseCategory(5, "Vestimenta"));
        ExpenseCategories.add(new ExpenseCategory(6, "Esparcimiento"));
        ExpenseCategories.add(new ExpenseCategory(7, "Deporte"));
        ExpenseCategories.add(new ExpenseCategory(8, "Otros"));
    }

    public static List<ExpenseCategory> getExpenseCategories() {
        return ExpenseCategories;
    }

    public int size() {
        return ExpenseCategories.size();
    }
}
