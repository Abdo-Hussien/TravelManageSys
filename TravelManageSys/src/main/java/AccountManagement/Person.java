package AccountManagement;
import java.util.Objects;

public class Person {

    protected String first_name;
    protected String last_name;
    protected String username;
    protected int age;
    protected String phone_number;
    protected String address;
    protected String password;
    protected String gender;
    protected String account_id; //given to user by a random method


    public Person(String first_name, String last_name, String username, int age, String phone_number, String address, String password, String gender, String account_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.age = age;
        this.phone_number = phone_number;
        this.address = address;
        this.password = password;
        this.gender = gender;
        this.account_id = account_id;
    }

    
    public Person() {
    }

}