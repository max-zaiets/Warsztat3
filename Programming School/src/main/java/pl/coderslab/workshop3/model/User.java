package pl.coderslab.workshop3.model;


import org.mindrot.jbcrypt.BCrypt;

public class User {

    private int id;
    private String email;
    private String username;
    private String password;

    private int usersGroupId;

    public User(){
    }

    public User(String email, String username, String password){
        this.email = email;
        this.username = username;
        hashPassword(password);
    }

    public void hashPassword(String password){
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUsersGroupId() {
        return usersGroupId;
    }

    public void setUsersGroupId(int user_group_id) {
        this.usersGroupId = user_group_id;
    }

    public void setPassword(String password) {
        this.password = password;
        hashPassword(password);
    }

    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
