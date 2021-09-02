package DAO;
/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/
import DatabaseConnections.ConnectionFactory;
import Model.User;
import java.sql.*;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsertUserDAOImpl implements InsertUser {

    public void insertUser(int id,User user, String tableName) {

        String sql = "INSERT INTO IF EXISTS" + tableName + "(name,surname,email,dob,age) VALUES(?,?,?,?,?)"+
                "INSERT INTO addresses()";

        Supplier<String> success = () -> "\nSUCCESSFULLY ADDED USER";

        Logger logger = Logger.getLogger(InsertUserDAOImpl.class.getSimpleName());
        Connection con = new ConnectionFactory().MakeConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(String.valueOf(sql))) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getDate_of_birth().toString());
            preparedStatement.setInt(5, user.getAge());

            int response = preparedStatement.executeUpdate();

            if (response > 0) {
                System.out.println(success.get() + "\nUUID:\t" + user.getId() + "\nName:\t" + user.getName());
                logger.log(Level.FINER, "Logging {0}",success.get() + "\nUUID:\t" +""+user.getId() + "\nName:\t" + user.getName());
            } else {
                preparedStatement.cancel();
                throw new SQLException("Failed to add user:\t" + user.getId() + " to the database");
            }
            con.getAutoCommit();

        } catch (Exception exception) {

            logger.log(Level.SEVERE,"Logging {0}", exception.getMessage());
        }

    }
    public void insertUsersInBulk(List<User> userList, String tableName){
        String sql = "INSERT INTO " + tableName + "(name,surname,email,dob,age) VALUES(?,?,?,?,?)";

        Supplier<String> success = () -> "\nSUCCESSFULLY ADDED USER";

        Logger logger = Logger.getLogger(InsertUserDAOImpl.class.getSimpleName());
        Connection con = new ConnectionFactory().MakeConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(String.valueOf(sql))) {
            userList.forEach((usr)->{
                try {
                    preparedStatement.setString(1, usr.getName());
                    preparedStatement.setString(2, usr.getEmail());
                    preparedStatement.setString(3, usr.getSurname());
                    preparedStatement.setString(4, usr.getDate_of_birth().toString());
                    preparedStatement.setInt(5, usr.getAge());
                    int response = preparedStatement.executeUpdate();
                    if (response > 0) {
                        userList.forEach(x->{
                            System.out.println(success.get()+"\n "+x);
                        });
                        System.out.println(success.get() + "\nUUID:\t" + usr.getId() + "\nName:\t" + usr.getName());
                        preparedStatement.cancel();
                    } else {
                        throw new SQLException("Failed to add user:\t" + usr.getId() + " to the database");
                    }
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }

            });


            con.getAutoCommit();

        } catch (Exception exception) {

            logger.log(Level.SEVERE,"Logging {0}", exception.getMessage());
        }

    }

}

