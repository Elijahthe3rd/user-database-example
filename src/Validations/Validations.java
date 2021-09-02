package Validations;

import Model.User;
import java.time.format.DateTimeFormatter;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Validations {
    //validate user name
    Predicate<User> isUserName = (user) -> user.getName().matches("^[A-Za-z]$");
    //validate user lastname
    Predicate<User> IsSurname = (user) -> user.getSurname().matches("^[A-Za-z]$");
    //validate user
    Predicate<User> IsEmail = (user) -> user.getEmail().matches("^[\\w-]@\\w{2,20}\\.\\w{2,3}$");
    //validate user
    Predicate<User> IsDob = (user) -> user.getDate_of_birth().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")).matches("\\d{4}-\\d{2}-\\d{2}");
    //validate age
    Predicate<User> IsAge = (x) -> {
        boolean v = false;
        if (String.valueOf(x.getAge()).matches("^[\\d+]$"))
            if (x.getAge() >= 0 && x.getAge() <= 200) v = true;
        return v;
    };

}

