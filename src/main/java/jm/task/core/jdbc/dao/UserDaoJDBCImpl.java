package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final String tableName = "users";
    private final Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    //создание таблицы
    public void createUsersTable() {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + tableName +
                     "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY , name VARCHAR(35) NOT NULL, " +
                     "lastName VARCHAR(35) NOT NULL, age MEDIUMINT NOT NULL) DEFAULT CHARSET=utf8")) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось создать таблицу!");
        }
    }

    //удаление таблицы
    public void dropUsersTable() {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS " + tableName)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Не удалось удалить таблицу");
        }

    }

    //добавление юзеров
    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + tableName + "(name , lastName, age) " +
                     " values ('" + name + "', '" + lastName + "', '" + age + "')")) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Не удалось сохранить пользователя!");
        }

    }

    //удаление юзеров
    public void removeUserById(long id) {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM " + tableName + " WHERE id = " + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Не удалось удалить пользователя");
        }

    }

    //получение юзеров
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM " + tableName)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось вернуть таблицу");
        }
        return users;
    }

    //очистка таблицы
    public void cleanUsersTable() {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE TABLE " + tableName)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Не удалось очистить таблицу");
        }

    }
}
