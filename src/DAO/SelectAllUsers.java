package DAO;
/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/
import BuilderFactory.Address;
import BuilderFactory.Dates;
import BuilderFactory.Name;
import DatabaseConnections.ConnectionFactory;
import Helpers.PrinterImpl;
import Model.User;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SelectAllUsers implements ViewTable {
    User u1;
    @Override
    public void selectAll(String userTableName,String addressTableName) throws SQLException {

        String query = "select * from user_data INNER JOIN addresses ON addresses.address_id=user_data.user_id ORDER BY last_login";
        ArrayList<User> users = new ArrayList<>();

        Connection con = new ConnectionFactory().MakeConnection();

        int UID ;
        String name;
        String surname;
        String email;
        String dob;
        Object address;
        int age;

        try (Statement stmt = con.createStatement()) {
            con.beginRequest();
            ResultSet rs = stmt.executeQuery(query);


                while (rs.next()) {
                    UID= rs.getInt(1);
                    name = rs.getString("name");
                    surname = rs.getString("surname");
                    email = rs.getString("email");
                    dob = rs.getString("dob").replaceAll("\\+\\d{2}", "").trim();
                    age = rs.getInt("age");


                    Dates builder = new Dates.Builder().dob(dob).age(age).build();
                    Name builder2 = new Name.Builder().name(name).surname(surname).build();

                    //working with address
                    try{
                    address= rs.getObject(8);
                    Stream<Object> addr=Stream.of(address);
                    String s= String.valueOf(addr.collect(Collectors.toList()).get(0));
                    s=s.replaceAll("\\W"," ").trim();
                    s=(s).replaceAll("\\W+",",");

                    String[]arr=s.split(",");
                    String streetName=arr[0];
                    String City=arr[1];
                    String State=arr[2];
                    String zipCode=arr[3];
                    Address builder3=new Address.Builder().street(streetName).state(State). city(City).zip(zipCode).build();

                    BuilderFactory.User userFactory = new BuilderFactory
                            .User
                            .Builder()
                            .id(UID)
                            .name(builder2)
                            .email(email)
                            .date(builder)
                            .address(builder3)
                            .build();
                    u1 = new User(userFactory.getId(), userFactory.getName().getName(), userFactory.getName().getSurname(),
                            userFactory.getEmail(), userFactory.getDates().getDate_of_birth(), userFactory.getAddress());
                    users.add(u1);

                }catch(Exception e){
                        System.out.println("\nSomething went wrong in class: "+this.getClass().getSimpleName()+"\n" +e.getMessage());
                        Logger logger=Logger.getLogger(SelectAllUsers.class.getName());
                        logger.log(Level.parse("DETAILS"),String.valueOf(e.getCause()));
                        con.clearWarnings();
                    }
                }
                PrinterImpl print=new PrinterImpl();
                print.printer(users);

        } catch (SQLException e) {
            con.close();
            e.printStackTrace();
        }
    }


}




