package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final String tableName = "users";
    Session session = Util.getSessionFactory().openSession();

    public UserDaoHibernateImpl() {
    }

    //создание таблицы юзеров
    @Override
    public void createUsersTable() {
        try {
            Transaction tx1 = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS " + tableName +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY , name VARCHAR(100) NOT NULL, " +
                    "lastName VARCHAR(100) NOT NULL, age MEDIUMINT NOT NULL) DEFAULT CHARSET=utf8")
                    .executeUpdate();
            tx1.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Таблица не создана");
        } finally {
            session.close();
        }
    }

    //удаление таблицы юзеров
    @Override
    public void dropUsersTable() {
        try {
            Transaction tx1 = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS " + tableName).executeUpdate();
            tx1.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Таблица не удалена");
        } finally {
            session.close();
        }
    }

    //добавление юзеров
    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            Transaction tx1 = session.beginTransaction();
            session.save(user);
            tx1.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Пользователь не добавлен");
        } finally {
            session.close();
        }
    }

    //удаление юзера по id
    @Override
    public void removeUserById(long id) {
        try {
            User user = (User) session.get(User.class, id);
            Transaction tx1 = session.beginTransaction();
            session.delete(user);
            tx1.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Пользователь не удален");
        } finally {
            session.close();
        }

    }

    //получение юзеров
    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try {
            String sql = "SELECT * FROM users";
            list = session.createSQLQuery(sql).addEntity(User.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Пользователи не получены");
        } finally {
            session.close();
        }
        return list;
    }

    //удаление всех юзеров
    @Override
    public void cleanUsersTable() {
        try {
            Transaction tx1 = session.beginTransaction();
            session.createSQLQuery("DELETE FROM " + tableName).executeUpdate();
            tx1.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Пользователи не удалены");
        } finally {
            session.close();
        }
    }
}
