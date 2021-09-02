package Model;
/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;


public class User implements Serializable,Comparable<Object> {

    private final int id;
    private final String name;
    private final String surname;
    private final String email;
    private final String date_of_birth;
    private int age;
    private final String address;

    public User(int id, String name, String surname, String email, String date_of_birth,String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.date_of_birth = date_of_birth;
        setAge(date_of_birth);
        this.address = address;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDate_of_birth() {
        String[] dates = date_of_birth.split("-");
        String year = (Integer.parseInt(dates[0]) > 0) ? dates[0] : "Dob provided will result in negative age";
        String month = (Integer.parseInt(dates[1]) > 0) ? dates[1] : "Dob provided will is invalid may result to negative output";
        String day = (Integer.parseInt(dates[2]) > 0) ? dates[2] : "Dob provided will is invalid may result to negative output";
        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }

    public int getAge() {
        return age;
    }

    private void setAge(String dob) {
        String[] dates = dob.split("-");
        String year = (Integer.parseInt(dates[0]) > 0) ? dates[0] : "Dob provided will result in negative age";
        String month = (Integer.parseInt(dates[1]) > 0) ? dates[1] : "Dob provided will is invalid may result to negative output";
        String day = (Integer.parseInt(dates[2]) > 0) ? dates[2] : "Dob provided will is invalid may result to negative output";
        LocalDate d = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        this.age = Period.between(d, LocalDate.now()).getYears();
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return ("\t\tUSER-PROVIDE\n" +
                "{\nID\t\t=\t'" + getId() + '\'' +
                "\nname\t=\t'" + getName() + '\'' +
                ", \nsurname\t=\t'" + getSurname() + '\'' +
                ", \nemail\t=\t'" + getEmail() + '\'' +
                ", \ndob\t\t=\t" + getDate_of_birth() + '\'' +
                ", \nage\t\t=\t" + age + "\n" +
                ", \naddress\t\t=\t" + getAddress()+  "\n"+
                '}');
    }

    @Override
    public int compareTo(Object o) {
        return this.compareTo(o);
    }
}
