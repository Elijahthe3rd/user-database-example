package CreateTables;

import DatabaseConnections.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Elijah Sepuru
 * @version 1.2
 * @since 2021/06/15
 **/
public class CreateTableImpl implements CreateTables {
    private final Scanner sc = new Scanner(System.in);
    private final String userTable;
    private final String addressTable;
    private final Logger logger = Logger.getLogger(CreateTableImpl.class.getName());

    public CreateTableImpl(String userTable, String addressTable) {
        this.userTable = userTable;
        this.addressTable = addressTable;
    }

    @Override
    public void createTables() {

        final Scanner sc = new Scanner(System.in);
        System.out.println("\nCreating tables:\n" + userTable + "\n" + addressTable + "\nplease wait...");
        System.out.println("\n(N.B) ID will be automatically generated on All tables.\n");
        String sql =
                "Drop table if exists " + addressTable + ";" +
                        "Drop table if exists " + userTable + ";" +
                        "create extension if not exists \"uuid-ossp\";" +
                        "create table if not exists " + userTable +" (user_id serial primary key,"+ "name VARCHAR(30) NOT NULL,"
                        + "surname VARCHAR(30) not null,"
                        + "email VARCHAR(100) not null,"
                        + "dob VARCHAR(50) not null," +
                        "age int,date_created date not null default(CURRENT_DATE),last_login timestamp NOT NULL DEFAULT NOW()); "
                        + "CREATE TABLE IF NOT EXISTS "
                        + addressTable +
                        " (address_id serial NOT NULL , street VARCHAR(40) NOT NULL,"
                        +
                        "city VARCHAR(30) NOT NULL,state VARCHAR(30) NOT NULL,zip_code BIGINT not null, primary key (address_id));";

        try (Connection con = new ConnectionFactory().MakeConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            System.out.println("Would you like Empty tables or populated tables?");
            System.out.println("For Empty Tables -> Enter Zero & for Populated Tables -> Enter 1:\n");
            int answer = sc.nextInt();
            if (answer == 0) {
                ps.executeUpdate();
                ps.close();
            } else if (answer == 1) {
                ps.executeUpdate();
                defaultTablesData();
            }
            System.out.println("\nTables created successfully.\n");

        } catch (SQLException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }

    public void defaultTablesData() throws SQLException {
        String sqlData = "INSERT INTO " + userTable + " (name,surname,email,dob,age) values('Elijah','Sepuru','elijah@commandquality.co.za','1993-04-26',28);" +
                "INSERT INTO " + userTable + " (name,surname,email,dob,age) values('sephula','mokoena','sephula.mokeona@umuzi.org','1989-09-01',32);" +
                "INSERT INTO " + userTable + " (name,surname,email,dob,age) values('Clive','Sepuru','mmaphuticlive@gmail.com','1997-02-26',24);" +
                "INSERT INTO " + addressTable + " (street,city,state,zip_code) values('111 commissioner','Gauteng','Gauteng',2001);" +
                "INSERT INTO " + addressTable + " (street,city,state,zip_code) values('396 Paradise Motel Street','Polokwane','Limpopo',0708);" +
                "INSERT INTO " + addressTable + " (street,city,state,zip_code) values('111 Commissioner','Polokwane','Limpopo',0800);";
        try (Connection connectionFactory = new ConnectionFactory().MakeConnection()) {
            connectionFactory.beginRequest();
            PreparedStatement ps = connectionFactory.prepareStatement(sqlData);
            ps.executeUpdate();
            System.out.println("TABLE " + userTable + " & " + addressTable + " WAS SUCCESSFULLY POPULATED.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            logger.log(Level.SEVERE, e.getMessage());
        }

    }
}
