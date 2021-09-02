package DAO;
/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/
import Model.User;
import java.util.List;

public interface InsertUser {

    void insertUser(int id,User user,String tableName) ;
    void insertUsersInBulk(List<User> userList, String tableName);
    default void insertUser(User user,String tableName)
    {

        insertUser(user.getId(),user,tableName);
    }
}
