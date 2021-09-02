package DAO;
/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/
import Model.User;
import java.sql.SQLException;

public interface UpdateUser {
    void updateUser(User user) throws SQLException;
}
