package pl.coderslab.workshop3.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    private DataSource dataSource = getInstance();

    public int create(String query, Object... params) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = generateStatement(conn, query, params);

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<List<Object>> read(String query, Object... params) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = generateStatement(conn, query, params);

            List<List<Object>> objects = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                List<Object> object = new ArrayList<>();

                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    object.add(resultSet.getInt(i));
                }

                objects.add(object);
            }

            return objects;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(String query, Object... params) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = generateStatement(conn, query, params);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(String query, Object... params) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = generateStatement(conn, query, params);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private PreparedStatement generateStatement(Connection conn, String query, Object... params) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        for (int i = 0; i < params.length; i++) {
            if (params[i] == null) {
                statement.setString(i + 1, "null");
            } else if (params[i] instanceof String) {
                statement.setString(i + 1, (String) params[i]);
            } else if (params[i] instanceof Integer) {
                statement.setInt(i + 1, (Integer) params[i]);
            }
        }
        return statement;
    }

    private DataSource getInstance() {
        if (dataSource == null) {
            try {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/programming_school");
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return dataSource;
    }
}
