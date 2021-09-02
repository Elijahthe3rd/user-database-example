package BuilderFactory;
/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

public class User {
    private final int id;
    private final String email;
    private final Name name;
    private final Dates dates;
    private final Age age;
    private final String address;

    public User(Builder builder){
        this.id=builder.id;
        this.name=builder.name;
        this.email=builder.email;
        this.dates=builder.dates;
        this.age=builder.age;
        this.address=builder.address;
    }

    public static class Builder{
        private int id;
        private String email;
        private Dates dates;
        private Name name;
        private Age age;
        private String address;

        public Builder id(final int id){
            this.id=id;
            return this;
        }

        public Builder email(final String email1)
        {
            this.email=email1;
            return this;
        }

        public Builder date(final Dates date){
            this.dates=date;
            return this;
        }
        public Builder name(final Name name){
            this.name=name;
            return this;
        }
        public Builder age(final Age age){
            this.age=age;
            return this;
        }
        public Builder address(final BuilderFactory.Address address){
            List<String> addresslst=new ArrayList<>();
            addresslst.add(address.getStreet());
            addresslst.add(address.getCity());
            addresslst.add(address.getState());
            addresslst.add(address.getZip());
            this.address= String.valueOf(addresslst.stream().collect(Collectors.toList()));
            return this;
        }
        public User build() {
            return new User(this);
        }
    }

    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public Name getName() {
        return name;
    }
    public Dates getDates() {
        return dates;
    }
    public Age getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
}
