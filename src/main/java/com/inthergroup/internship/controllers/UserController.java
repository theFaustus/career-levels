package com.inthergroup.internship.controllers;

import com.inthergroup.internship.models.CareerLevel;
import com.inthergroup.internship.models.User;
import com.inthergroup.internship.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author interns
 */
@Controller
public class UserController {
    
    // ------------------------
    // PRIVATE FIELDS
    // ------------------------
    
    @Autowired
    private UserService userService;

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * /create-user  --> Create a new user and save it in the database.
   * 
   * @param email User's email
   * @param name User's name
   * @return A string describing if the user is succesfully created or not.
   */
  @RequestMapping("/create-user")
  @ResponseBody
  public String createUser(String lastName, String firstName, String login,
                       String password, String email, String levelName) {
    User user = null;
    CareerLevel careerLevel = new CareerLevel(levelName);
    try {
      user = new User(lastName, firstName, login,
                      password, email, careerLevel);
      userService.create(user);
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created! (id = " + user.getId() + ")";
  }
  
  /**
   * /delete-user  --> Delete the user having the passed id.
   * 
   * @param id The id of the user to delete
   * @return A string describing if the user is succesfully deleted or not.
   */
  @RequestMapping("/delete-user")
  @ResponseBody
  public String deleteUser(long id) {
    try {
      userService.deleteById(id);
    }
    catch (Exception ex) {
      return "Error deleting the user by id #" + id + ": " + ex.toString();
    }
    return "User succesfully deleted!";
  }
  
  /**
   * /get-by-id  --> Return information about user using his id.
   * 
   * @param id The id to search in the database.
   * @return A message with information about the user or a message error
   * if the user is not found.
   */
  @RequestMapping("/get-user-by-id")
  @ResponseBody
  public String getUserById(long id) {
      User user = null;
      try {
          user = userService.findById(id);
      }
      catch (Exception ex) {
          return "User not found";
      }
      return "id: " + user.getId() + ", last name: " + user.getLastName() +
             ", first name: " + user.getFirstName() +
             ", career level: " + user.getCareerLevel();
  }
  
  /**
   * /update-user  --> Update the email and the name for the user in the database 
   * having the passed id.
   * 
   * @param id The id for the user to update.
   * @param email The new email.
   * @param name The new name.
   * @return A string describing if the user is succesfully updated or not.
   */
  @RequestMapping("/update-user")
  @ResponseBody
  public String updateUser(long id, String lastName, String firstName, String login,
                           String password, String email, String levelName) {
    try {
      CareerLevel careerLevel = new CareerLevel(levelName);
      // careerLevelService.create(careerLevel);
      User user = userService.findById(id);
      user.setLastName(lastName);
      user.setFirstName(firstName);
      user.setLogin(login);
      user.setPassword(password);
      user.setEmail(email);
      user.getCareerLevel().setLevelName(levelName);
      userService.create(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  }  
} // class UserController