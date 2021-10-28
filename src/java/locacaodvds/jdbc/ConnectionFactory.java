package locacaodvds.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Uma fábrica de conexões.
 *
 * @author Prof. Dr. David Buzatto
 */
public class ConnectionFactory {
  
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(
                "jdbc:mariadb://localhost/locacao_dvds",
                "root",
                "" );

    }
}
