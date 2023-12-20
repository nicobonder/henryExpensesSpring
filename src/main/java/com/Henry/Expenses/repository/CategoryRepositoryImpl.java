/*
package com.Henry.Expenses.repository;

import com.Henry.Expenses.domain.categories.ExpenseCategory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    // Otros atributos y constructores

    private static final String GET_CATEGORY_BY_NAME = "SELECT * FROM ExpenseCategory WHERE name = ?";

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ExpenseCategory getByName(String categoryName) {
        return jdbcTemplate.queryForObject(GET_CATEGORY_BY_NAME, new Object[]{categoryName}, new ExpenseRepositoryImpl.ExpenseCategoryRowMapper());
    }

    // Otros métodos y clases necesarios
}
*/
