package repository;

import Excepcions.RepositoryExepcion;
import domain.categories.ExpenseCategory;
import dto.ExpenseCategoryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRepositoryImpl implements CategoryRepository {
    private static final String GET_CATEGORY_BY_NAME = "SELECT * FROM expenseCategory WHERE categoryName = ?";

    private final Connection connection;

    public CategoryRepositoryImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void insert(ExpenseCategoryDto category) {

    }

    @Override
    public ExpenseCategory getCategoryById(String categoryName) {
        try (PreparedStatement statement = connection.prepareStatement(GET_CATEGORY_BY_NAME)) {
            statement.setString(1, categoryName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new ExpenseCategory(
                        resultSet.getInt("id"),
                        resultSet.getString("categoryName")
                );
            }
            return null;
        } catch (SQLException e) {
            try {
                throw new RepositoryExepcion("Error al obtener la categoria por ID", e);
            } catch (RepositoryExepcion ex) {
                throw new RuntimeException(ex);
            }
        }
    }


}
