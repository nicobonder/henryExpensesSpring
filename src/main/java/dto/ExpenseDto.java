package dto;

import domain.categories.ExpenseCategory;

public class ExpenseDto {

    private int id;
    private String date;
    private double amount;
    private ExpenseCategory category;


    public ExpenseDto(int id, String date, double amount, ExpenseCategory category) {
        this.id = id;
        this.amount = amount;
        this.category = category;

        // Define el formato de fecha
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

        this.date = date;
    }
    public ExpenseDto(String date, double amount, ExpenseCategory category) {
        this.amount = amount;
        this.category = category;

        // Define el formato de fecha
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

        this.date = date;
    }

    public ExpenseDto() {
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
