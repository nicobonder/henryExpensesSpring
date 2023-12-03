package Excepcions;

import java.sql.SQLException;

public class RepositoryExepcion extends Exception {
    public RepositoryExepcion(String message) {
        super(message);
    }

    ;

    public RepositoryExepcion(String message, SQLException e) {
        super(message);
    }

    ;
}