package AccountManagement;

public interface Personsinterface {
    String getFirst_name();

    String getLast_name();

    String getUsername();

    int getAge();

    String getPhone_number();

    String getAddress();

    String getPassword();

    String getGender();

    String getAccount_id();

    int getTripHistoryCounter();

    void setFirst_name(String first_name);

    void setLast_name(String last_name);

    void setUsername(String username);

    void setAge(int age);

    void setPhone_number(String phone_number);

    void setAddress(String street, String state, String zip);

    String getStreetAddress();

    void setStreetAddress(String streetAddress);

    String getStateAddress();

    void setStateAddress(String stateAddress);

    String getZipAddress();

    void setZipAddress(String zipAddress);

    void setPassword(String password);

    void setGender(String gender);
}
