package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      Car carOne = new Car("BMW", 55);
      Car carTwo = new Car("Mercedes", 66);
      Car carThree = new Car("Porsche", 77);
      Car carFour = new Car("Fiat", 88);


      userService.add(new User("User1", "Lastname1", "user1@mail.ru", carOne));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", carTwo));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));



      List<User> users = userService.listUsers();
      User userOne = userService.getUserByCarModelAndSeries("BMW", 55);
      System.out.println(userOne.getCar());
      System.out.println();
      User userTwo = userService.getUserByCarModelAndSeries("Mercedes", 66);
      System.out.println(userTwo.getCar());
      System.out.println();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " +user.getCar());
      }


      context.close();
   }
}
