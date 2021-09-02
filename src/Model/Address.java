package Model;

import java.io.Serializable;

/**
 * @author Elijah Sepuru
 * @version 1.0
 * @since 2021/06/15
 **/

public class Address implements Serializable , Comparable<Object> {

    private final int id;
    private final int houseNo;
    private final String street;
    private final String city;
    private final String state;
    private final int zip;


    /**
     * @param id the id
     * @param aStreet the street
     * @param houseNo houseNo
     * @param aCity the city
     * @param aState the two-letter state code
     * @param aZip the aZIP postal code
    */
    public Address(int id,int houseNo,String aStreet,
                  String aCity, String aState, int aZip)
    {
        this.id = id;
        street = aStreet;
        city = aCity;
        state = aState;
        zip = aZip;
        this.houseNo=houseNo;
    }

    public int getId() {
        return id;
    }
    /**
     * @return street
     */
    public String getStreet() {

        return street;
    }
    /**
     * @return city
     */
    public String getCity() {

        return city;
    }
    /**
     * @return state
     */
    public String getState() {

        return state;
    }
    /**
     * @return zip
     */
    public int getZip() {

        return zip;
    }
    /**
     * @return houseNo
     */
    public int getHouseNo() {
        return houseNo;
    }

    public String getAddress(){
        return (getId()+":\t"+getHouseNo()+" "+getStreet()+","+getCity()+", "+getState()+", "+getZip());
    }

    @Override
    public int compareTo(Object o) {
        return this.compareTo(o);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id\t=\t" + id +
                ", houseNo\t=\t" + houseNo +
                ", street\t=\t'" + street + '\'' +
                ", city\t=\t'" + city + '\'' +
                ", state\t=\t'" + state + '\'' +
                ", zip\t=\t" + zip +
                '}';
    }
}
