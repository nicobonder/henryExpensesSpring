package repository;

import Excepcions.RepositoryExepcion;
import domain.categories.ExpenseCategory;
import domain.expenses.Expense;
import dto.ExpenseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseRepositoryImpl implements ExpenseRepository {
    //DECLARO TODAS LAS SENTENCIAS SQL QUE VOY A USAR

    private static final String GET_CATEGORY_BY_ID = "SELECT * FROM expenseCategory WHERE id = ?";
    private static final String GET_EXPENSE_BY_ID = "SELECT * FROM expense WHERE id = ?";
    private static final String GET_ALL_EXPENSES = "SELECT * FROM expense";
    private static final String INSERT_ALL_EXPENSE = "INSERT INTO expense (date, amount, category_id) VALUES (?, ?, ?)";
    private static final String UPDATE_EXPENSE = "UPDATE expense SET date = ?, amount = ?, category_id = ? WHERE id = ?";
    private static final String DELETE_EXPENSE = "DELETE FROM expense WHERE id = ?";

    //GENERO UNA INSTANCIA DE CONEXION PARA USAR LOS METODOS DEL OBJ CONNECTION.
    private final Connection connection;

    // SE HACE CON UN CONSTRUCTOR
    public ExpenseRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
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
    public ExpenseDto getExpenseById(int id) {
        ExpenseDto expenseDto = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_EXPENSE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String date = resultSet.getString("date");
                double amount = resultSet.getDouble("amount");
                int categoryId = resultSet.getInt("category_id");
                ExpenseCategory category = getExpenseCategoryById(categoryId);
                expenseDto = new ExpenseDto(date, amount, category);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return expenseDto;
    }

    @Override
    public void addExpense(ExpenseDto expenseDto) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ALL_EXPENSE)) {
            //OBTENGO un DTO PERO A LA DB TENGO QUE MANIPULAR UNA ENTIDAD. PARA ESO MAPEO
            Expense expense = mapDtoToEntity(expenseDto);

            preparedStatement.setString(1, expense.getDate());
            preparedStatement.setDouble(2, expense.getAmount());
            preparedStatement.setInt(3, expense.getCategory().getId());

            //Esto devuelve un entero executeUpdate()
            int affectedRows = preparedStatement.executeUpdate();

            //La uso para hacer una validacion
            if (affectedRows == 0) {
                throw new RepositoryExepcion("Error al insertar gasto. Ninguna fila fue afectada");
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (RepositoryExepcion e) {
            throw new RuntimeException(e);
        }
    }

    //Metodo para mapear de Dto a Entity para manipular en la DB
    private Expense mapDtoToEntity(ExpenseDto expenseDto) {
        //seteo el Expense con los valores que vienen del Dto
        Expense expense = new Expense();
        expense.setDate(expenseDto.getDate());
        expense.setAmount(expenseDto.getAmount());
        expense.setCategory(expenseDto.getCategory());

        return expense;
    }

    @Override
    public List<ExpenseDto> getAllExpenses() throws RepositoryExepcion {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EXPENSES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ExpenseDto> expenses = new ArrayList<>();
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
    private ExpenseDto mapResultSetToExpenseDto(ResultSet resultSet) throws SQLException {
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setDate(resultSet.getString("date"));
        expenseDto.setAmount(resultSet.getDouble("amount"));
        expenseDto.setCategory(getExpenseCategoryById(resultSet.getInt("category_id")));
        return expenseDto;
    }

    @Override
    public void updateExpense(ExpenseDto expense) {
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
    }

}
