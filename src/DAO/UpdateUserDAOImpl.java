package DAO;
/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/

import DatabaseConnections.ConnectionFactory;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class UpdateUserDAOImpl implements UpdateUser {
    private final Logger logger = Logger.getLogger(UpdateUser.class.getName());

    Supplier<String> failure = () -> "\nData update was unsuccessful.\n";

    @Override
    public void updateUser(User user) throws SQLException {
        String sql = createUpdateQuery();
        Supplier<String> success = () -> "\nRECORD DATA UPDATED SUCCESSFULLY";

        Logger logger = Logger.getLogger(InsertUserDAOImpl.class.getName());

        Connection con = new ConnectionFactory().MakeConnection();

        System.out.println(sql);

        try (
                PreparedStatement statement = con.prepareStatement(sql)) {
            int response = statement.executeUpdate();
            System.out.println(response);
            if (response > 0) {
                System.out.println(success.get() + "\nUUID:\t" + user.getId() + "\nName:\t" + user.getName());
                logger.log(Level.FINER, "Logging {0}", success.get() + "\nUUID:\t" + user.getId() + "\nName:\t" + user.getName());
            } else {
                throw new SQLException(failure.get() + user.getId() + "to the database");
            }
        } catch (Exception exception) {
            con.close();
            logger.log(Level.SEVERE, "Logging {0}", exception.getMessage());
        }

    }

    public String createUpdateQuery() {
        String userChoice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter the name of the table you wish to update?\n");
        String tableName = sc.next();

        if (!(tableName.matches("[A-Za-z]+[^\\W\\S\\D]/g")) && (tableName.length() < 0)) {
            logger.log(Level.WARNING, new IllegalStateException("Unexpected/Invalid value: " + tableName).getMessage());
            throw new IllegalStateException("Unexpected/Invalid value: " + tableName);
        }

        System.out.println("What would you like to update?\n");
        String argument = sc.next();

        if (argument.length() > 0 && argument.matches("[A-Za-z]")) {
            logger.log(Level.WARNING, new IllegalStateException("Unexpected/Invalid value: " + argument).getMessage());
            throw new IllegalStateException("Unexpected/Invalid value: " + argument);
        }
        System.out.printf("Please enter the unique user id from table:'" +
                "%s' for the user you wish to update?\n", tableName);
        int uniqueUserId = sc.nextInt();
        if (uniqueUserId <= 0) {
            logger.log(Level.WARNING, new IllegalStateException("Unexpected/Invalid value: " + uniqueUserId).getMessage());
            throw new IllegalStateException("Unexpected/Invalid value: " + uniqueUserId);
        }

        System.out.println("Please enter the " + argument + " you wish to update\n");
        String argumentInput;
        argumentInput = sc.next();
        StringBuilder sql = new StringBuilder();
        try {

            userChoice = argument.toLowerCase(Locale.getDefault());
            switch (argument) {
                case "name":
                case "surname":

//      update userdb2 SET address='{"111 Commissioner","Joburg City",Gauteng,2001}' where id='6204be67-fe0c-4ae3-906c-6f276065168f';
                    sql.append("UPDATE " + tableName + " SET " + argument + "='" + argumentInput + "'" + " WHERE id='" + uniqueUserId + "'");
                    break;
                case "email":
                case "dob", "date of birth":
                    sql.append("UPDATE " + tableName + " SET " + argument + "='" + argumentInput + "'" + " WHERE id='" + uniqueUserId + "'");
                    break;
                case "age":
                    sql.append("UPDATE " + tableName + " SET " + argument + "='" + argumentInput + "'" + " WHERE id='" + uniqueUserId + "'");
                    break;
                default:
                    throw new IllegalStateException("Unexpected/Invalid value: " + userChoice);
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(("Something went wrong:\n\t\t\t" + illegalArgumentException.getMessage()));
        }
        return sql.toString();
    }

}
