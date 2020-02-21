package pl.coderslab.workshop3.dao;

import pl.coderslab.workshop3.model.UsersGroups;
import pl.coderslab.workshop3.utils.GetConnection;

import java.sql.*;
import java.util.Arrays;

public class UsersGroupsDAO {
    private static final String CREATE_USERSGROUP_QUERY =
            "INSERT INTO users_groups(name) VALUES (?)";
    private static final String READ_USERSGROUP_QUERY =
            "SELECT * FROM users_groups where id = ?";
    private static final String UPDATE_USERSGROUP_QUERY =
            "UPDATE users_groups SET name = ? where id = ?";
    private static final String DELETE_USERSGROUP_QUERY =
            "DELETE FROM users_groups WHERE id = ?";
    private static final String FIND_ALL_USERSGROUPS_QUERY =
            "SELECT * FROM users_groups";

    public UsersGroups create(UsersGroups usersGroups) {
        try (Connection conn = GetConnection.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USERSGROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, usersGroups.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                usersGroups.setId(resultSet.getInt(1));
            }
            return usersGroups;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UsersGroups read(int usersGroupId) {
        try (Connection conn = GetConnection.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USERSGROUP_QUERY);
            statement.setInt(1, usersGroupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UsersGroups usersGroups = new UsersGroups();
                usersGroups.setId(resultSet.getInt("id"));
                usersGroups.setName(resultSet.getString("name"));
                return usersGroups;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(UsersGroups usersGroups) {
        try (Connection conn = GetConnection.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USERSGROUP_QUERY);
            statement.setString(1, usersGroups.getName());
            statement.setInt(2, usersGroups.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(UsersGroups usersGroups){
        delete(usersGroups.getId());
    }

    public void delete(int usersGroupId) {
        try (Connection conn = GetConnection.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USERSGROUP_QUERY);
            statement.setInt(1, usersGroupId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private UsersGroups[] addToArray(UsersGroups u, UsersGroups[] usersGroups) {
        UsersGroups[] tmpUsersGroups = Arrays.copyOf(usersGroups, usersGroups.length + 1);
        tmpUsersGroups[usersGroups.length] = u;
        return tmpUsersGroups;
    }

    public UsersGroups[] findAll() {
        try (Connection conn = GetConnection.getConnection()) {
            UsersGroups[] usersGroups = new UsersGroups[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERSGROUPS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UsersGroups usersGroup = new UsersGroups();
                usersGroup.setId(resultSet.getInt("id"));
                usersGroup.setName(resultSet.getString("name"));
                usersGroups = addToArray(usersGroup, usersGroups);
            }
            return usersGroups;
        } catch (SQLException e) {
            e.printStackTrace(); return null;
        }}

}
