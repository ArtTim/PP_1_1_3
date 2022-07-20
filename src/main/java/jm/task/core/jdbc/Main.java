package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

//!1) Для каждой задачи должен быть свой репозиторий и 1 ветка.
//!2) Работа с БД должна быть в дао слое. Сервис вызывает методы дао.
//!3) PreparedStatement использовать только там, где он нужен.
//!4) Statement нет необходимости использовать как ресурс.
//!5) Добавь логику транзакций.

//!1) getSessionFactory() не нужен в этой задаче.
//!2) В сервисе сделай поле типа интерфейса.
//!3) Нужна @Entity?
//!4) Каждый метод дао должен открывать соединение, работать с ним и закрывать. Можно использовать в try-with-resources.
//!5) Нет отката транзакций в случае ошибки.




public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Vyacheslav", "Popov", (byte)15);
        userService.saveUser("Sergey", "Buza", (byte)27);
        userService.saveUser("Igor", "Dolinnik", (byte)25);
        userService.saveUser("Timur", "Bulatov", (byte)25);

        userService.removeUserById(2);
        userService.getAllUsers();

        for(User el : userService.getAllUsers()) {
            System.out.println(el);
        }
        System.out.println("После удаления пользователя:");
        userService.getAllUsers();
        for(User el : userService.getAllUsers()) {
            System.out.println(el);
        }
//        userService.dropUsersTable();
        System.out.println("После удаления таблицы:");
        userService.getAllUsers();
        for(User el : userService.getAllUsers()) {
            System.out.println(el);
        }

    }
}
