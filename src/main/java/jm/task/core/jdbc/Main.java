package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();


        userService.saveUser("Maxic", "Crazy", (byte) 33);
        userService.saveUser("Petr", "One", (byte) 25);
        userService.saveUser("Ivan", "Grozny", (byte) 30);
        userService.saveUser("Lara", "Croft", (byte) 26);


        List<User> listUsers = userService.getAllUsers();
        for (User us : listUsers) {
            System.out.println(us);

            userService.removeUserById(2);
            userService.cleanUsersTable();
            userService.dropUsersTable();
        }


    }
}
