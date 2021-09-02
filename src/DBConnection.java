import Helpers.MenuImpl;
import Helpers.Menu;
import java.sql.SQLException;
/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/
public class DBConnection {

    public static void main(String[] args) throws SQLException {
        Menu menu = new Menu();
        MenuImpl menuChoice=new MenuImpl();
        System.out.println(menu.displayMenu());
        menuChoice.menuChoice.CheckUserInput("user_data","addresses");
    }


}
