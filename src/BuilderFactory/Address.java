
package BuilderFactory;
/**
 * @author Elijah Sepuru
 * @version 1.1
 * @since 2021/06/15
 **/
public class Address {
    private final String street;
    private final String city;
    private final String state;
    private final String zip;

    public Address(Builder builder){
        this.street = builder.street;
        this.city = builder.city;
        this.state = builder.state;
        this.zip = builder.zip;
    }
    public static class Builder {
        private  String street;
        private  String city;
        private  String state;
        private  String zip;

        public Builder street(final String  str){
            street=str;
            return this;
        }

        public Builder city(final String  city){
            this.city=city;
            return this;
        }
        public Builder state(final String  state){
            this.state=state;
            return this;
        }
        public Builder zip(final String  zip){
            this.zip=zip;
            return this;
        }
        public Address build(){
            return new Address(this);
        }
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }
}
