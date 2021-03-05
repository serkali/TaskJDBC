package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final String tableName = "users";

    public UserDaoHibernateImpl() {
    }

    //создание таблицы юзеров
    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS " + tableName +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY , name VARCHAR(100) NOT NULL, " +
                "lastName VARCHAR(100) NOT NULL, age MEDIUMINT NOT NULL) DEFAULT CHARSET=utf8")
                .executeUpdate();
        tx1.commit();
        session.close();
        System.out.println("Таблица создана");
    }

    //удаление таблицы юзеров
    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS " + tableName).executeUpdate();
        tx1.commit();
        session.close();
        System.out.println("Таблица удалена");
    }

    //добавление юзеров
    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
        System.out.println("Пользователь добавлен");
    }

    //удаление юзера по id
    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        User user = (User) session.get(User.class, id);
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
        System.out.println("Пользователь удален");
    }

    //получение юзеров
    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        String sql = "SELECT * FROM users";
        List<User> list = session.createSQLQuery(sql).addEntity(User.class).list();
        session.close();
        return list;

    }


    //удаление всех юзеров
    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.createSQLQuery("DELETE FROM " + tableName).executeUpdate();
        tx1.commit();
        session.close();
        System.out.println("Пользователи удалены");
    }
}
