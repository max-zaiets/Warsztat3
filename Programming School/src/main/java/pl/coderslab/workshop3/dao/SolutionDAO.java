package pl.coderslab.workshop3.dao;

import pl.coderslab.workshop3.model.Exercise;
import pl.coderslab.workshop3.model.Solution;
import pl.coderslab.workshop3.model.User;
import pl.coderslab.workshop3.utils.DBUtils;

import java.sql.*;
import java.util.Arrays;

public class SolutionDAO {
    private static final String CREATE_SOLUTION_QUERY =
            "INSERT INTO solutions(created, updated, description) VALUES (?, ?, ?)";
    private static final String READ_SOLUTION_QUERY =
            "SELECT * FROM solutions where id = ?";
    private static final String UPDATE_SOLUTION_QUERY =
            "UPDATE users SET created = ?, updated = ?, description = ? where id = ?";
    private static final String DELETE_SOLUTION_QUERY =
            "DELETE FROM solutions WHERE id = ?";
    private static final String FIND_ALL_SOLUTION_QUERY =
            "SELECT * FROM solutions";
    private static final String FIND_ALL_SOLUTION_BY_USER_ID_QUERY =
            "SELECT * FROM solutions WHERE user_id = ?";
    private static final String FIND_ALL_SOLUTION_BY_EXERCISE_ID_QUERY =
            "SELECT * FROM solutions WHERE exercise_id = ?";
    private static final String FIND_RECENT_SOLUTIONS_QUERY =
            "SELECT * FROM solutions ORDER BY created DESC LIMIT ?";


    public Solution create(Solution solution) {
        try (Connection conn = DBUtils.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, (Date) solution.getCreated());
            statement.setDate(2, (Date) solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solution read(int solutionId) {
        try (Connection conn = DBUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Solution solution) {
        try (Connection conn = DBUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setDate(1, (Date) solution.getCreated());
            statement.setDate(2, (Date) solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Solution solution) {
        delete(solution.getId());
    }

    public void delete(int solutionId) {
        try (Connection conn = DBUtils.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Solution[] addToArray(Solution s, Solution[] solutions) {
        Solution[] tmpSolutions = Arrays.copyOf(solutions, solutions.length + 1);
        tmpSolutions[solutions.length] = s;
        return tmpSolutions;
    }

    public Solution[] findAll() {
        try (Connection conn = DBUtils.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTION_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                solutions = addToArray(solution, solutions);
            }
            return solutions;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public Solution[] findAllByUserId(User user) throws SQLException {
        try (Connection conn = DBUtils.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTION_BY_USER_ID_QUERY);
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                solutions = addToArray(solution, solutions);
            }
            return solutions;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Solution[] findAllByExerciseId(Exercise exercise) throws SQLException {
        try (Connection conn = DBUtils.getConnection()) {
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTION_BY_EXERCISE_ID_QUERY);
            statement.setInt(1, exercise.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                solutions = addToArray(solution, solutions);
            }
            return solutions;

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Solution[] findRecent (int limit) throws SQLException {
        try(Connection conn = DBUtils.getConnection()){
            Solution[] solutions = new Solution[0];
            PreparedStatement statement = conn.prepareStatement(FIND_RECENT_SOLUTIONS_QUERY);
            statement.setInt(1, limit);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
