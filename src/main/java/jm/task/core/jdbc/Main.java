package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Vlad", "Ok", (byte)15);
        userService.saveUser("Sergey", "Buza", (byte)27);
        userService.saveUser("Igor", "Dolinnik", (byte)25);
        userService.getAllUsers();
        for(User el : userService.getAllUsers()) {
            System.out.println(el);
        }
        userService.removeUserById(3);
        System.out.println("После удаления:");
        userService.getAllUsers();
        for(User el : userService.getAllUsers()) {
            System.out.println(el);
        }
        userService.dropUsersTable();

    }
}
