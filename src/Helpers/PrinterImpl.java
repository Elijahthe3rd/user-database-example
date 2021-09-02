package Helpers;
/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/
import Model.User;
import java.util.ArrayList;

public class PrinterImpl {
    private final String columns = "|\tUID\t\t|\tNAME\t|\tLASTNAME\t|\t\t\tEMAIL\t\t\t\t\t" + "|\t\tDOB\t\t\t\t|\tAGE\t\t|\t\t\t\tADDRESS\t\t\t\t\t|";
    private StringBuilder sb = new StringBuilder();
    private String rows;

    public void printer(ArrayList<User> userList) {
        final int i1 = (columns.length() * 2) - columns.length();
        for (int i = 0; i < i1 +11; i++) {
            if (i == 0) {
                sb.append("+");
            }  else {
                if(i== i1 +10){
                    sb.append("-+");

                }else{
                    sb.append("--");

                }
            }
        }

        System.out.println("\r");
        System.out.println(sb);
        System.out.println(columns);
        System.out.println(sb);

        for (User user : userList
        ) {
            rows = "|\t"
                    + user.getId()
                    + "\t\t|\t"
                    + user.getName()
                    + "\t|\t"
                    + user.getSurname()
                    + "\t\t|\t"
                    + user.getEmail()
                    + "\t\t|"
                    + String.valueOf(user.getDate_of_birth()).replaceAll("^[\\d{2}]$", "")
                    + "\t\t\t\t|"
                    + user.getAge()
                    + "\t\t\t|\t"+user.getAddress() +"\t\t\t|";

            System.out.println(rows);
        }
        System.out.println(sb.replace(sb.length() - 1, sb.length(), "+"));

    }
}
