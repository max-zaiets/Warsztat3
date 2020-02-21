package pl.coderslab.workshop3;

import pl.coderslab.workshop3.dao.UserDAO;
import pl.coderslab.workshop3.model.User;

import java.util.Scanner;


public class Main1 {
    public static void main(String[] args) {
        System.out.println("List of users: ");

        UserDAO userDAO = new UserDAO();
        User[] listOfUsers = userDAO.findAll();
        for (User element : listOfUsers) {
            System.out.println(element);
        }
        System.out.println("");
        System.out.println("Please select one of the following: ");
        System.out.println("\n -add \n -edit \n -delete \n -quit");


        Scanner scanner = new Scanner(System.in);
        String choice = scanner.next();

            switch (choice) {
                case "add":
                    addUser();
                    User[] updatedList = userDAO.findAll();
                    for (User element : updatedList) {
                        System.out.println(element);
                    }
                    break;
                case "edit":
                    System.out.println("Please provide the user's id: ");
                    scanner = new Scanner(System.in);
                    int userId = scanner.nextInt();
                    updateUser(userId);

                    User[] editedList = userDAO.findAll();
                    for (User element : editedList) {
                        System.out.println(element);
                    }
                    break;
                case "delete":
                    System.out.println("Please provide the user's id: ");
                    scanner = new Scanner(System.in);
                    int userIdDelete = scanner.nextInt();
                    deleteUser(userIdDelete);

                    User[] delList = userDAO.findAll();
                    for (User element : delList) {
                        System.out.println(element);
                    }
                    break;
                case "quit":
                    break;
            }
    }

    public static void addUser(){
        UserDAO userDAO = new UserDAO();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide username: ");
        scanner = new Scanner(System.in);
        String username = scanner.next();

        System.out.println("Please provide your email: ");
        scanner = new Scanner(System.in);
        String email = scanner.next();

        System.out.println("Please set password: ");
        scanner = new Scanner(System.in);
        String password = scanner.next();
        User user = new User(email, username, password);
        userDAO.create(user);
    }

    public static void updateUser(int userId){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide new username: ");
        String newUsername = scanner.next();

        scanner = new Scanner(System.in);
        System.out.println("Please provide your new email: ");
        String newEmail = scanner.next();

        scanner = new Scanner(System.in);
        System.out.println("Please update your password: ");
        String newPassword = scanner.next();

        User user = new User();
        user.setId(userId);
        user.setUsername(newUsername);
        user.setEmail(newEmail);
        user.setPassword(newPassword);

        UserDAO userDAO = new UserDAO();
        userDAO.update(user);
    }

    public static void deleteUser(int userId){
        UserDAO userDAO = new UserDAO();
        userDAO.delete(userId);
    }

}
