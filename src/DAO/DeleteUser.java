package DAO;
/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/
import java.sql.SQLException;

public interface DeleteUser {
    int deleteUser(String email,String tableName) throws SQLException;
}
