package com.Henry.Expenses.domain.categories;

public class ExpenseCategory {
    private Long id;
    private String categoryName;

    public ExpenseCategory(Long id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }

    public ExpenseCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
