package Sweet.system;

import java.util.ArrayList;
import java.util.List;

public class Login {
    String admin="admin";
    String user="user";
    public List<User> users = new ArrayList<User>();
    public List<StoreOwner> storeOwners = new ArrayList<>();
    public List<Providor> providors=new ArrayList<>();
    private boolean logInStatus;
public Login() {
initializeUsers();
}
    public void initializeUsers() {

            User u1 = new User("alid2003@gmail.com","123", admin);
            User u2 = new User("m2n@gmail.com","123", user);
            User u3 = new User("mtar123@gmail.com","123", user);
            StoreOwner s1=new StoreOwner("mota12@gmail.com","12","Nablus" );
        StoreOwner s2=new StoreOwner("motar2@gmail.com","12","Jenin" );
        StoreOwner s3=new StoreOwner("moa123@gmail.com","12","Nablus" );
users.add(u1);
users.add(u2);
users.add(u3);
storeOwners.add(s1);
storeOwners.add(s2);
storeOwners.add(s3);
    logInStatus=false;

    }

    public boolean isLoggedIn() {
        return this.logInStatus;
    }

    public void setLogInStatus(boolean logInStatus) {
        this.logInStatus = logInStatus;
    }

    public int getTypeNumber(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) return 0; // Invalid credentials

        for (User i : users) {
            if (i.getEmail().equals(email) && i.getPassword().equals(password)) {
                logInStatus = true;
                 return 1;
            }
        }
for(StoreOwner i: storeOwners){
    if (i.getEmail().equals(email) && i.getPassword().equals(password)) {
        logInStatus = true;
        return 2;
    }

}
for(Providor i:providors){
    if (i.getEmail().equals(email) && i.getPassword().equals(password)) {
        logInStatus = true;
        return 3;
    }
}
return 0;
    }

    public boolean emailExists(String email){
        for (User i :
                users) {
            if (i.getEmail().equals(email) ) {
                return true;
            }
        }
        return false;
    }

    public User getCurrentUser(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) return null;

        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    public void addUser(String email, String password) {
        User newUser = new User(email, password, user);
        users.add(newUser);
    }


    public void deleteUser(String email) {
        int toRemove;
        for (User i: users){
            if(i.email.equals(email)){
                toRemove = users.indexOf(i);
                users.remove(toRemove);
                break;
            }
        }
    }

    public void addServiceProvider(String email, String password){
        Providor newProvidor = new Providor(email, password);
        providors.add(newProvidor);
    }

    public void addStoreOwner(String email, String password, String city) {
    StoreOwner newSo=new StoreOwner(email,password,city);
    storeOwners.add(newSo);
    }
}
