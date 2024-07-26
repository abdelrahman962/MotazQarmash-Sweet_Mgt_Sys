package Sweet.system;

import java.util.Objects;

public class User {
    String email;
     String password;
  String role;
    boolean admin=false;
    public User(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }
public boolean isAdmin( ) {

        return Objects.equals(this.getRole(), "admin");
}
    public String getEmail() {
        return email;
    }

    public void setAsAdmin() {
        this.admin = true;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

}
