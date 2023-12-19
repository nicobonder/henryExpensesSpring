package com.Henry.Expenses.dto;

public class ExpenseCategoryDto {
    private String categoryName;

    public ExpenseCategoryDto(String categoryName) {
        this.categoryName = categoryName;
    }

    public void ExpenseCategoryDto() {
    }

    public String getCategoryName() {
        return categoryName.toLowerCase();
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return categoryName + '\''
                ;
    }
}
