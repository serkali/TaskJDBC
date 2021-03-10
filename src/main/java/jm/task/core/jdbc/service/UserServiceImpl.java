package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoHibernateImpl dao = new UserDaoHibernateImpl();


    //создание таблицы
    public void createUsersTable() {
        dao.createUsersTable();
    }

    //удаление таблицы
    public void dropUsersTable() {
        dao.dropUsersTable();

    }

    // добавление USER  в таблицу
    public void saveUser(String name, String lastName, byte age) {
        dao.saveUser(name, lastName, age);


    }

    // удаление User из таблицы по id
    public void removeUserById(long id) {
        dao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    // очистка содержания таблицы
    public void cleanUsersTable() {
        dao.cleanUsersTable();
    }
}
