package com.inthergroup.internship.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inthergroup.internship.models.Todo;
import com.inthergroup.internship.services.TodoService;

@Controller
public class TodoController {
    
    @Autowired
    private TodoService todoService;

    @RequestMapping("/create-todo")
    @ResponseBody
    public String createTodo(String task) {
        Todo todo = null;
        try {
            todo = new Todo(task);
            todoService.create(todo);
        } catch (Exception ex) {
            return "Error creating the Todo: " + ex.toString();
        }
        return "Todo succesfully created! (id = " + todo.getId() + ")";
    }

    @RequestMapping("/add-todo-to-user")
    @ResponseBody
    @Transactional
    public String addTodoToUser(long userId, long todoId) {
        try {
            todoService.addTodoToUser(userId, todoId);
        } catch (Exception ex) {
            return "Error adding todo to user: " + ex.toString();
        }
        return "Succesfully added todo to user!";
    }

    // You must delete todo only after you deleted it from all
    // users that contained it.
    @RequestMapping("/delete-todo")
    @ResponseBody
    public String deleteTodo(long id) {
        try {
            todoService.deleteById(id);
        } catch (Exception ex) {
            return "Error deleting the todo by id #" + id + ": " + ex.toString();
        }
        return "Todo succesfully deleted!";
    }
    
    @RequestMapping("/remove-todo-from-user")
    @ResponseBody
    @Transactional
    public String removeTodoFromUser(long userId, long todoId) {
        try {
            todoService.removeTodoFromUser(userId, todoId);
        } catch (Exception ex) {
            return "Error removing todo from user: " + ex.toString();
        }
        return "Succesfully removed todo from user!";
    }
    
} // class GroupController
