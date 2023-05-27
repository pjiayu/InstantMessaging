package Pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 皮皮皮
 * @date 2023/5/25 16:56
 */
public class User {
    int id;
    private String username;
    private String password;
    private int age;
    private String profile;

    private List<User>friendsList=new ArrayList<>();

    //小助手
//    private User assistant;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, int age, String profile) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.profile = profile;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public List<User> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(List<User> friendsList) {
        this.friendsList = friendsList;
    }
    @Override
    public String toString(){
        return "User{" +
                "userID=" + id+
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
