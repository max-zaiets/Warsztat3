package pl.coderslab.workshop3;

import pl.coderslab.workshop3.dao.UserDAO;
import pl.coderslab.workshop3.dao.UsersGroupsDAO;
import pl.coderslab.workshop3.model.User;
import pl.coderslab.workshop3.model.UsersGroups;

import java.sql.SQLException;
import java.util.Arrays;

public class Application1 {
    public static void main(String[] args) throws SQLException {
        //ExerciseDAO exerciseDAO = new ExerciseDAO();
       // Exercise ex1 = new Exercise("gdrgr", "dthdtht");

       // ex1 = exerciseDAO.create(ex1);
       // System.out.println(ex1);


        UserDAO userDAO = new UserDAO();
        User userRead1 = userDAO.read(1);
        UsersGroupsDAO usersGroupsDAO = new UsersGroupsDAO();
        UsersGroups usersGroups = new UsersGroups("first group");
        usersGroups = usersGroupsDAO.create(usersGroups);

        UsersGroups usersGroups1 = new UsersGroups();
        usersGroups1 = usersGroupsDAO.read(1);
       // if (userRead1 != null){
       //     userRead1.setUsername("hthfhthft");
       //     userDAO.update(userRead1);
       // }

       // System.out.println(userRead1);//each time we want to manipulate with the database through java objects
        // we need to create a different object in order to avoid calling multiple methods

       // SolutionDAO solutionDAO = new SolutionDAO();
       // Solution[] sol = solutionDAO.findAllByUserId(userRead1);
       // System.out.println(Arrays.toString(sol));

     User[] users = userDAO.findAllByGroupId(usersGroups1);
     System.out.println(Arrays.toString(users));


    }

}
