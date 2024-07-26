package Sweet.system;

public class StoreOwner {
    private String email;
    private String password;
    private String city;
    public StoreOwner(String email, String password, String city) {
        this.email = email;
        this.password = password;
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
public String getCity() {
        return city;
}

}
