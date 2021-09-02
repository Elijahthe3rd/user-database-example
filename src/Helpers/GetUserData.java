package Helpers;

/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/

import Model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetUserData {

    private final Scanner sc = new Scanner(System.in);

    public List<User> getUserData() {

        System.out.println("How many users do you wish  to add?\n");

        Scanner scanner = new Scanner(System.in);
        int userCount = 0;
        try {
            userCount = scanner.nextInt();
        } catch (IllegalArgumentException ex) {
            new IllegalArgumentException("Some went wrong").printStackTrace();
        }

        List<User> lst = new ArrayList<>();
        List<String> addressList=new ArrayList<>();


        do {

            int id = 0;
            System.out.println("Default Unique User ID(UUID) Auto-Genereted: " + ++id );
            System.out.println("What is your name:\n");
            String name = sc.next().trim();
            System.out.println("What is your surname:\n");
            String surname = sc.next().trim();
            System.out.println("\n");
            System.out.println("What is your email:\n");
            String email = sc.next().trim();
            System.out.println("\n");
            System.out.println("Enter your date of birth (YYYY-MM-DD)::\n");
            String dob = sc.next();
            System.out.println("\n");
            System.out.println("Please Enter your House No.");
            addressList.add(String.valueOf(new Scanner(System.in).nextInt()));
            System.out.println("Please Enter your street name");
            addressList.add(sc.next());
            System.out.println("Please Enter your city name");
            addressList.add(sc.next());
            System.out.println("Please Enter your state/province name");
            addressList.add(sc.next());
            System.out.println("Please Enter your province's zip-code");
            addressList.add(sc.next());
            String addr =
                    addressList.get(0)
                    + " " +
                    addressList.get(1)
                    + ", " +
                    addressList.get(2)
                    + ", " +
                    addressList.get(3)
                    + ", " +
                    addressList.get(4);

            User p = new User( id, name, surname, email, dob, addr );
            lst.add( p );

            if (userCount == 0) {
                System.out.println("No input provided please provide your Information");
                getUserData();
            } else {
                break;
            }
            userCount--;
            StringBuilder msg = new StringBuilder("Hello " + p.getName() + " " + p.getSurname() + " your details have been saved to our database:\n\nBelow is Info you provided\n");

            int lengthAfter = lst.size();
            if (lengthAfter > 0 && userCount != 0) {
                System.out.println(msg);
            } else {
                System.out.println(new StringBuilder("Something went wrong: was not saved to our database"));
            }

        } while (userCount > 0);
        return lst;
    }
}
