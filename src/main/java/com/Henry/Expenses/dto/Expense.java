package com.Henry.Expenses.dto;

public class Expense {

    private Integer id;
    private double amount;
    private Long categoryId;
    private String CategoryName;
    private String date;


    public Expense(double amount, Long categoryId, String categoryName, String date) {
        this.amount = amount;
        this.categoryId = categoryId;
        CategoryName = categoryName;
        this.date = date;
    }

    public Expense() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
