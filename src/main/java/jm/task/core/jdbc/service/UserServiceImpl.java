package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl  implements UserService {
    private UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();


    //создание таблицы
    public void createUsersTable() {
        userDaoJDBC.createUsersTable();
    }

    //удаление таблицы
    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();

    }

    // добавление USER  в таблицу
    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBC.saveUser(name,lastName,age);


    }

    // удаление User из таблицы по id
    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoJDBC.getAllUsers();
    }

    // очистка содержания таблицы
    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();
    }
}
