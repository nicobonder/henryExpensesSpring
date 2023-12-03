package repository;

import domain.categories.ExpenseCategory;
import dto.ExpenseCategoryDto;

public interface CategoryRepository {
    void insert(ExpenseCategoryDto category);
    ExpenseCategory getCategoryById(String categoryName);

}