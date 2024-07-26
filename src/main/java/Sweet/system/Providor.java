package Sweet.system;

public class Providor {
    private String email;
    private String password;

    public Providor(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}