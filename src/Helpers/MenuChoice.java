package Helpers;
/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/
import java.sql.SQLException;

public interface MenuChoice {
    int CheckUserInput(String userTableName,String addressTableName) throws SQLException;
}
