package com.inthergroup.internship.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inthergroup.internship.models.CareerLevel;
import com.inthergroup.internship.models.User;
import com.inthergroup.internship.services.BenefitService;
import com.inthergroup.internship.services.CareerLevelService;
import com.inthergroup.internship.services.TodoService;
import com.inthergroup.internship.services.UserService;

@Controller
public class HomeController {

    @Autowired
    private CareerLevelService careerLevelService;
    
    @Autowired
    private BenefitService benefitService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TodoService todoService;

    /**
     * Prepares data for main page.
     * 
     * @param model
     * @return
     */
    @RequestMapping({"/", "/index"})
    public String index(Model model) {

        // List all career levels
        List<CareerLevel> careerLevels = careerLevelService.findAllByOrderByIdAsc();
        model.addAttribute("careerLevels", careerLevels);
        

        // For each career level list its benefits
        
        List<List<Object[]>> benefits = new ArrayList<List<Object[]>>();
        for (long i = 1; i <= careerLevels.size(); i++) {
            List<Object[]> benefitsFromLevel = benefitService.findBenefitsFromLevel(i);
            benefits.add(benefitsFromLevel);
        }
        model.addAttribute("benefits", benefits);
        
        // For each career level list its users
        List<List<User>> users = new ArrayList<List<User>>();
        for (long i = 1; i <= careerLevels.size(); i++) {
            List<User> usersFromLevel = userService.findUsersFromLevel(i);
            users.add(usersFromLevel);
        }
        model.addAttribute("users", users);

        // For each career level list its todos
        List<List<Object[]>> todos = new ArrayList<List<Object[]>>();
        for (long i = 1; i <= careerLevels.size(); i++) {
            List<Object[]> todosFromLevel = todoService.findTodosFromLevel(i);
            todos.add(todosFromLevel);
        }
        model.addAttribute("todos", todos);
        
        // Map with percent progress for corresponding user id.
        model.addAttribute(
                "usersPercentProgress", userService.findUsersPercentProgress());

        return "index";
    }
} // class HomeController
