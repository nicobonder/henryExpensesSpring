package domain.expenses;

import domain.categories.ExpenseCategory;
import org.springframework.stereotype.Component;

@Component
public class Expense {
    private static int idCounter = 1;
    private int id;

    //variable para registrar fecha
    private String date;
    private double amount;
    private ExpenseCategory category;


    public Expense(String date, double amount, ExpenseCategory category) {
        this.id = idCounter++;
        this.amount = amount;
        this.category = category;

        // Define el formato de fecha
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

        this.date = date;
    }

    public Expense() {
        id++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "Expense:" +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", category=" + category
                ;
    }

}

