package BuilderFactory;
/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/

public class Name {
    private final String name;
    private final String surname;

    private Name(Builder builder) {
        this.name=builder.name;
        this.surname=builder.surname;
    }

    public static class Builder {
        private String name;
        private String surname;

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder surname(final String surname) {
            this.surname = surname;
            return this;
        }
        public Name build(){
            return new Name(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
