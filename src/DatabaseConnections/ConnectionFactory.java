package DatabaseConnections;
/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionFactory {


    private final Supplier<Integer> port = () -> 5432;
    private final Supplier<String> jdbcUrl = () -> "jdbc:postgresql://localhost:" + port.get() + "/";
    private final Supplier<String> dbpassword = () -> "pgpassword";
    private final Supplier<String> dbUser = () -> "pguser";
    private final Supplier<String> dbName = () -> "pguser";
    private Connection connection;

    private Logger logger = Logger.getLogger(ConnectionFactory.class.getName());

    public ConnectionFactory() { }

    public final void jdbcDriverClassPath(String driver) {
        try {
            if (driver != null || driver.matches("^[\\w][\\W\\S\\D][\\w][\\W\\S\\D]$"))
                Class.forName(driver);
            System.out.println("Driver registered to the class Path: "+driver);
        } catch (ClassCastException | ClassNotFoundException classCastException) {
            logger.fine(classCastException.getMessage());
            System.out.println();
        }
    }

    public final Connection MakeConnection() {
        jdbcDriverClassPath("org.postgres.Driver");
        Connection con = null;
        try {
            con = DriverManager.getConnection(jdbcUrl.get() + dbUser.get(), dbName.get(), dbpassword.get());
            if (con != null) {
                logger.log(Level.INFO, "DATABASE CONNECTION WAS SUCCESSFUL");
            }
        } catch (SQLException sqlException) {
            logger.log(Level.WARNING, sqlException.getMessage());
        }
        connection = con;
        return con;
    }
}