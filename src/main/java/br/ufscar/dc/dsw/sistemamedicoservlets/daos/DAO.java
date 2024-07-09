package br.ufscar.dc.dsw.sistemamedicoservlets.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DAO {

    public DAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/dsw_sistema_medico";
        return DriverManager.getConnection(url, "admin", "admin");
    }
}
