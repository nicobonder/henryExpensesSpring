package repository;

import Excepcions.RepositoryExepcion;
import domain.categories.ExpenseCategory;
import dto.Expense;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ExpenseRepositoryImpl implements ExpenseRepository {
    //DECLARO TODAS LAS SENTENCIAS SQL QUE VOY A USAR

    private static final String INSERT_INTO_EXPENSE = "INSERT INTO Expense (amount, category_id, category_name, date) VALUES (?, ?, ?, ?)";
    private static final String INSERT_INTO_CATEGORY_EXPENSE = "INSERT INTO ExpenseCategory (name) VALUES (?)";
    private static final String SELECT_FROM_CATEGORY_EXPENSE_BY_NAME = "SELECT * FROM ExpenseCategory WHERE name = ?";
    /*private static final String GET_CATEGORY_BY_ID = "SELECT * FROM expenseCategory WHERE id = ?";
    private static final String GET_EXPENSE_BY_ID = "SELECT * FROM Expense WHERE id = ?";
    private static final String GET_ALL_EXPENSES = "SELECT * FROM Expense";
    private static final String UPDATE_EXPENSE = "UPDATE Expense SET date = ?, amount = ?, category_id = ? WHERE id = ?";
    private static final String DELETE_EXPENSE = "DELETE FROM Expense WHERE id = ?";*/

    //Uso JdbcTamplate para conectarme a la DB
    private final JdbcTemplate jdbcTemplate;

    public ExpenseRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer insert(Expense expense) {
       jdbcTemplate.update(INSERT_INTO_CATEGORY_EXPENSE, expense.getCategoryName().toLowerCase());
        Object[] params = {expense.getCategoryName()};
        int[] types = {1};
        ExpenseCategory category = jdbcTemplate.queryForObject(
            SELECT_FROM_CATEGORY_EXPENSE_BY_NAME,
                params, types, new ExpenseCategoryRowMapper()
        );
        return jdbcTemplate.update(INSERT_INTO_EXPENSE,
                expense.getAmount(),
                category.getId(),
                category.getCategoryName(),
                expense.getDate());
    }

    static class ExpenseCategoryRowMapper implements RowMapper<ExpenseCategory> {
        @Override
        public ExpenseCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
           ExpenseCategory expenseCategory = new ExpenseCategory();
           expenseCategory.setId(rs.getLong("id"));
           expenseCategory.setCategoryName(rs.getString("name"));
           return expenseCategory;
        }
    }





  /*  @Override
    public ExpenseCategory getExpenseCategoryById(int id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_CATEGORY_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int idCategory = resultSet.getInt("id");
                String name = resultSet.getString("categoryName");
                return new ExpenseCategory(idCategory, name);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    @Override
    public Expense getExpenseById(int id) {
        Expense expense = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_EXPENSE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String date = resultSet.getString("date");
                double amount = resultSet.getDouble("amount");
                int categoryId = resultSet.getInt("category_id");
                ExpenseCategory category = getExpenseCategoryById(categoryId);
                expense = new Expense(date, amount, category);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return expense;
    }


    @Override
    public List<Expense> getAllExpenses() throws RepositoryExepcion {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EXPENSES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Expense> expenses = new ArrayList<>();
            while (resultSet.next()) {
                //DE LA DB RECUPERO ENTITIES Y QUIERO PASAR A OBJETOS DE TIPO DTO
                expenses.add(mapResultSetToExpenseDto(resultSet));
            }
            return expenses;
        } catch (SQLException e) {
            try {
                throw new RepositoryExepcion("Error al obtener los gastos", e);
            } catch (RepositoryExepcion ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    //Seteo el Dto con los valores que vienen del objeto de la DB
    private Expense mapResultSetToExpenseDto(ResultSet resultSet) throws SQLException {
        Expense expense = new Expense();
        expense.setDate(resultSet.getString("date"));
        expense.setAmount(resultSet.getDouble("amount"));
        expense.setCategory(getExpenseCategoryById(resultSet.getInt("category_id")));
        return expense;
    }

    @Override
    public void updateExpense(Expense expense) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EXPENSE)) {

            //Expense expense = mapDtoToEntity(expenseDto);

            preparedStatement.setString(1, expense.getDate());
            preparedStatement.setDouble(2, expense.getAmount());
            preparedStatement.setInt(3, expense.getCategory().getId());
            preparedStatement.setInt(4, expense.getId()); //id del gasto que quiero actualizar

            //Esto devuelve un entero executeUpdate()
            int affectedRows = preparedStatement.executeUpdate();

            //La uso para hacer una validacion
            if (affectedRows == 0) {
                throw new RepositoryExepcion("Error al insertar gasto. Ninguna fila fue afectada");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (RepositoryExepcion e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteExpense(int expenseId) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EXPENSE)) {
            preparedStatement.setInt(1, expenseId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/

}
