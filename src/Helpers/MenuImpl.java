package Helpers;
/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/

import DAO.DeleteUserDAOImpl;
import DAO.InsertUserDAOImpl;
import DAO.SelectAllUsers;
import DAO.UpdateUserDAOImpl;
import Model.User;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Logger;

public final class MenuImpl {

    private final ArrayList < User > userList = new ArrayList <>();
    private final GetUserData UserData = new GetUserData();
    private final Scanner sc = new Scanner( System.in );

    public MenuImpl() {
    }
    public MenuChoice menuChoice = ( String x, String y ) -> {

        Predicate < String > isTableName = ( x1 ) ->
                ((x1.trim().length() > 0) && (x1 instanceof String) && (x1.trim() != null));
        Predicate < String > isAddressTableName = ( y1 ) ->
                (y1.trim().length() > 0) && (y1 instanceof String) && (y1.trim() != null);


        Supplier < String > userTableName = ( ) -> isTableName.test( x) ? x : null;
        Supplier < String > addressTableName = () -> isTableName.test( y ) ? y : null;

        Logger logger = Logger.getLogger( MenuImpl.class.getName() );

        System.out.println( "\nPlease select one Number / x option from the Menu:\n" );

        char choice = sc.next().charAt( 0 );

        int numberVal = Integer.parseInt( Character.toString( choice ) );

        switch (numberVal) {
            case 1:
                userList.addAll( UserData.getUserData().stream().toList() );
                InsertUserDAOImpl insertUser = new InsertUserDAOImpl();
                for ( User user : userList ) {
                    insertUser.insertUser( user, userTableName.get() );
                }
                break;
            case 2:
                UpdateUserDAOImpl update = new UpdateUserDAOImpl();
                update.createUpdateQuery();
                break;
            case 3:
                DeleteUserDAOImpl deleteUser = new DeleteUserDAOImpl();
                System.out.println( "Please enter email:\n" );
                String email = sc.next();
                deleteUser.deleteUser( email, userTableName.get() );
                break;
            case 4:
                SelectAllUsers viewFullTable = new SelectAllUsers();
                viewFullTable.selectAll( userTableName.get(), "addresses" );
                break;
            case 5: {
                System.out.println( "Are you Sure you want to CLOSE/STOP the program" );
                System.out.println( "\nPlease select Enter option y/Y or n/N:\n" );
                choice = sc.next().charAt( 0 );
                switch (choice) {
                    case 'y':
                    case 'Y':
                        System.out.println( "Good Bye." );
                        System.exit( 0 );

                    case 'n':
                    case 'N':
                        Menu menu = new Menu();
                        System.out.println( menu.displayMenu() );
                        MenuImpl menuChoice = new MenuImpl();
                        menuChoice.menuChoice.CheckUserInput( "user_data","addresses" );
                }
            }
            break;
            default:
                System.out.println( "Invalid Selected option." + numberVal );
        }
        return numberVal;
    };


}
