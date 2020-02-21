package pl.coderslab.workshop3.dao;

import pl.coderslab.workshop3.model.User;
import pl.coderslab.workshop3.utils.DBUtils;;import java.util.ArrayList;
import java.util.List;


public class UserDAO {
    private DBUtils dbUtil = new DBUtils();

    public int create(User user) {
        int userId = dbUtil.create(
                "INSERT INTO users(username, email, password) VALUES (?, ?, ?)",
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );

        return userId;
    }

    public List<User> read(User user) {
        List<List<Object>> objects = dbUtil.read(
                "SELECT * FROM users where id = ?",
                user.getId()
        );

        List<User> users = objectsToUsers(objects);
        return users;
    }

    public void update(User user) {
        dbUtil.update(
                "UPDATE users SET username = ?, email = ?, password = ? where id = ?",
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getId()
        );
    }

    public void delete(User user) {
        dbUtil.delete(
                "DELETE FROM users WHERE id = ?",
                user.getId()
        );
    }

    public List<User> findAll() {
        List<List<Object>> objects = dbUtil.read("SELECT * FROM users");

        List<User> users = objectsToUsers(objects);
        return users;
    }

    public List<User> findAllByGroupId(User user) {
        List<List<Object>> objects = dbUtil.read("SELECT * FROM users WHERE user_group_id = ?", user.getUsersGroupId());

        List<User> users = objectsToUsers(objects);
        return users;
    }

    private List<User> objectsToUsers(List<List<Object>> objects) {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < objects.size(); i++) {
            User newUser = new User();
            List<Object> object = objects.get(i);

            newUser.setId((Integer) object.get(0));
            newUser.setUsername((String) object.get(1));
            newUser.setEmail((String) object.get(2));
            newUser.setPassword((String) object.get(3));

            users.add(newUser);
        }
        return users;
    }
}