package BuilderFactory;
/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/

import java.time.LocalDate;
import java.time.Period;

public class Dates {
    private final String date_of_birth;
    private int age;

    public Dates(Builder builder) {
        this.date_of_birth = builder.date_of_birth;
        this.age = builder.age;
    }

    public static class Builder{

        private String date_of_birth;
        private int age;

        public Builder dob(final String dob) {
            setDate_of_birth(dob);
            this.date_of_birth=dob;
            setAge(dob);
            return this;
        }

        public Builder age(final int age) {
            this.age=age;
            return this;
        }

        private void setDate_of_birth(final String date_of_birth) {
            String[] dates = date_of_birth.split("-");
            String year = (Integer.parseInt(dates[0]) > 0) ? dates[0] : "Dob provided will result in negative age";
            String month = (Integer.parseInt(dates[1]) > 0) ? dates[1] : "Dob provided will is invalid may result to negative output";
            String day = (Integer.parseInt(dates[2]) > 0) ? dates[2] : "Dob provided will is invalid may result to negative output";
            LocalDate d = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            this.date_of_birth = date_of_birth;
        }
        private void setAge(final String date_of_birth) {
            String[] dates = date_of_birth.split("-");
            String year = (Integer.parseInt(dates[0]) > 0) ? dates[0] : "Dob provided will result in negative age";
            String month = (Integer.parseInt(dates[1]) > 0) ? dates[1] : "Dob provided will is invalid may result to negative output";
            String day = (Integer.parseInt(dates[2]) > 0) ? dates[2] : "Dob provided will is invalid may result to negative output";
            LocalDate d = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            this.age = Period.between(d,LocalDate.now()).getYears();
        }

        public Dates build(){
            return new Dates(this);
        }
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public int getAge() {
        return age;
    }
}
