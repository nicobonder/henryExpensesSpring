package com.Henry.Expenses.domain.expenses;

import org.springframework.stereotype.Component;

@Component
public class Expense {
   private Long id;
   private Double amount;
   private Integer categoryId;
   private String categoryName;
   private String date;

    public Expense() {
    }

    public Expense(Double amount, Integer categoryId, String categoryName, String date) {
        this.amount = amount;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}


/*
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
 */