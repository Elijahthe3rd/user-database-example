package BuilderFactory;
/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/
public class Age {
    private final int age;

    public Age(Builder builder) {
        this.age = builder.age;
    }
    public static class Builder {
        int age;

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Age build() {
            return new Age(this);
        }
    }

    public int getAge() {
        return age;
    }
}
