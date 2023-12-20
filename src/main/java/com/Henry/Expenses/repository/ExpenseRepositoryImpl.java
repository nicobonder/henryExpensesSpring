package com.Henry.Expenses.repository;

import com.Henry.Expenses.domain.categories.ExpenseCategory;
import com.Henry.Expenses.dto.Expense;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
    private static final String GET_ALL_EXPENSES = "SELECT * FROM Expense";
    private static final String GET_EXPENSE_BY_ID = "SELECT * FROM Expense WHERE id = ?";
    private static final String DELETE_EXPENSE = "DELETE FROM Expense WHERE id = ?";
    private static final String UPDATE_EXPENSE_CATEGORYID = "UPDATE Expense SET category_id = ? WHERE id = ?";

    private static final String UPDATE_EXPENSE = "UPDATE Expense SET amount = ?, date = ? WHERE id = ?";
    private static final String CATEGORY_NAME = "SELECT category_name FROM Expense WHERE id = ?";
    private static final String UPDATE_CATEGORY_NAME = "UPDATE Expense SET category_name = ? WHERE id = ?";


    //Uso JdbcTamplate para conectarme a la DB
    private final JdbcTemplate jdbcTemplate;

    public ExpenseRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer insert(Expense expense) {
        String categoryName = expense.getCategoryName().toLowerCase();

        // Intentar obtener la categoría existente
        List<ExpenseCategory> categories = jdbcTemplate.query(
                SELECT_FROM_CATEGORY_EXPENSE_BY_NAME,
                new Object[]{categoryName},
                new ExpenseCategoryRowMapper()
        );

        if (categories.isEmpty()) {
            // La categoría no existe, así que la insertamos
            jdbcTemplate.update(INSERT_INTO_CATEGORY_EXPENSE, categoryName);

            // Recuperamos la categoría recién insertada
            categories = jdbcTemplate.query(
                    SELECT_FROM_CATEGORY_EXPENSE_BY_NAME,
                    new Object[]{categoryName},
                    new ExpenseCategoryRowMapper()
            );
        }

        // Tomamos el primer resultado (si existe)
        ExpenseCategory category = categories.isEmpty() ? null : categories.get(0);

        if (category != null) {
            // Insertamos el gasto utilizando la categoría existente o recién creada
            return jdbcTemplate.update(INSERT_INTO_EXPENSE,
                    expense.getAmount(),
                    category.getId(),
                    category.getCategoryName(),
                    expense.getDate());
        } else {
            // Manejar el caso de que no se haya encontrado ni insertado la categoría
            return 0;
        }
    }

    @Override
    public Integer update(Long id, Expense expense) {
        // Obtener el nombre actual de la categoría en el gasto
        String currentCategoryName = jdbcTemplate.queryForObject(
                CATEGORY_NAME,
                new Object[]{id},
                String.class
        );

        // Verificar si el nombre de la categoría ha cambiado
        if (!Objects.equals(currentCategoryName, expense.getCategoryName())) {
            // Actualizar el nombre de la categoría en el gasto
            jdbcTemplate.update(
                    UPDATE_CATEGORY_NAME,
                    expense.getCategoryName(),
                    id
            );

            // Intentar obtener la categoría existente con el nuevo nombre
            List<ExpenseCategory> categories = jdbcTemplate.query(
                    SELECT_FROM_CATEGORY_EXPENSE_BY_NAME,
                    new Object[]{expense.getCategoryName()},
                    new ExpenseCategoryRowMapper()
            );

            // Tomar el primer resultado (si existe)
            ExpenseCategory category = categories.isEmpty() ? null : categories.get(0);

            if (category != null) {
                // Actualizar el ID de la categoría en el gasto
                jdbcTemplate.update(
                        UPDATE_EXPENSE_CATEGORYID,
                        category.getId(),
                        id
                );
            }
        }

        // Actualizar el resto de los campos en el gasto
        return jdbcTemplate.update(
                UPDATE_EXPENSE,
                expense.getAmount(),
                expense.getDate(),
                id
        );
    }


    @Override
    public ArrayList<Expense> getAll() {
        return new ArrayList<>(jdbcTemplate.query(GET_ALL_EXPENSES, new ExpenseRowMapper()));
    }

    @Override
    public Expense getById(Long id) {
        return jdbcTemplate.queryForObject(GET_EXPENSE_BY_ID, new Object[]{id}, new ExpenseRowMapper());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE_EXPENSE, id);
    }

    public class ExpenseRowMapper implements RowMapper<Expense> {
        @Override
        public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
            Expense expense = new Expense();
            expense.setId(rs.getInt("id"));
            expense.setAmount(rs.getDouble("amount"));
            expense.setCategoryId(rs.getLong("category_id"));
            expense.setCategoryName(rs.getString("category_name"));
            expense.setDate(rs.getString("date"));

            return expense;
        }
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
