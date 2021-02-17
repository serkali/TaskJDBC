package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        try {
            userService.saveUser("Maxic","Crazy", (byte) 33);
            userService.saveUser("Petr","One", (byte) 25);
            userService.saveUser("Ivan","Grozny", (byte) 30);
            userService.saveUser("Lara","Croft", (byte) 26);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            List<User>listUsers = userService.getAllUsers();
            for (User us : listUsers){
                System.out.println(us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userService.removeUserById(2);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }


}
