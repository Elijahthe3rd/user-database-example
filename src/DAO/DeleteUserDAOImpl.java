package DAO;
/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/
import DatabaseConnections.ConnectionFactory;
import java.sql.*;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class DeleteUserDAOImpl implements DeleteUser {

    @Override
    public int deleteUser(String email,String table) {

        String sql = "DELETE FROM user_data WHERE email=\'"+email+"\'"+"DELETE FROM addresses address_id=user_id";

        Supplier<String> success = () -> "Successfully deleted user";

        Logger logger = Logger.getLogger(DeleteUserDAOImpl.class.getName());

        try {
            Connection con = new ConnectionFactory().MakeConnection() ;

            Statement statement = con.createStatement();
            int response = statement.executeUpdate(sql);
            if (response > 0) {
                System.out.println( success.get() + "with email:" + email);
            } else {
                statement.cancel();
                con.close();
                logger.log(Level.FINER,"could not delete user with email:" + email);

                throw new SQLException("could not delete user with email:" + email);
            }
        } catch (Exception exception) {
            logger.log(Level.SEVERE, exception.getMessage());

        }

        return 1;
    }
}
