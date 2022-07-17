package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    Connection connection = Util.getConnection();

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `users` (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NULL, age INT NULL, PRIMARY KEY (id))";

        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS `users`";

        try (Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into `users` (name, lastName, age) values (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM `users` WHERE id= ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "select name, lastName, age from `users`";

        try (Statement statement = connection.prepareStatement(sql)){

            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM `users`";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
