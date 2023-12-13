package AccountManagement;

public interface Personsinterface {
    public String getFirst_name();

    public String getLast_name();

    public String getUsername();

    public int getAge();

    public String getPhone_number();

    public String getAddress();

    public String getPassword();

    public String getGender();

    public String getAccount_id();

    public int getTripHistoryCounter();

    public void setFirst_name(String first_name);

    public void setLast_name(String last_name);

    public void setUsername(String username);

    public void setAge(int age);

    public void setPhone_number(String phone_number);

    public void setAddress(String street, String state, String zip);

    public String getStreetAddress();

    public void setStreetAddress(String streetAddress);

    public String getStateAddress();

    public void setStateAddress(String stateAddress);

    public String getZipAddress();

    public void setZipAddress(String zipAddress);

    public void setPassword(String password);

    public void setGender(String gender);
}
