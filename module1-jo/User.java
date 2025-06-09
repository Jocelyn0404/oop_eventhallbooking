package module1-jo;

public class User {
    protected String userId, name, email, password;

    public User (String id, String n, String e, String pw){
        userId = id;
        name = n;
        email = e;
        password = pw;
    }

    public boolean login (String email, String password){
        return this.email.equals(email) && this.password.equals(password);
    }

    public void logout() {
        System.out.println(name + " hass logged out.");
    }

    public void updateProfile (String n, String e){
        name = n;
        email = e;
        System.out.println("Profile updated successfully.");
    }

    public String getUserId(){
        return userId;
    }

    public String getName(){
        return name;
    }
}