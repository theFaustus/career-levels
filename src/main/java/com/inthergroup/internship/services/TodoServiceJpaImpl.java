package com.inthergroup.internship.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inthergroup.internship.models.CareerLevel;
import com.inthergroup.internship.models.Todo;
import com.inthergroup.internship.models.User;
import com.inthergroup.internship.repositories.CareerLevelRepository;
import com.inthergroup.internship.repositories.TodoRepository;
import com.inthergroup.internship.repositories.UserRepository;

@Service
@Primary
public class TodoServiceJpaImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private CareerLevelRepository careerLevelRepo;

    @Override
    public List<Todo> findAll() {
        return this.todoRepo.findAll();
    }

    @Override
    public Todo findById(Long id) {
        return this.todoRepo.findOne(id);
    }

    @Override
    public Todo create(Todo todo) {
        return this.todoRepo.save(todo);
    }

    @Override
    public Todo edit(Todo todo) {
        return this.todoRepo.save(todo);
    }

    @Override
    public void deleteById(Long id) {
        this.todoRepo.delete(id);
    }
    
    @Override
    @Transactional
    public void addTodoToUser(Long userId, Long todoId) {
        Todo todo = todoRepo.findOne(todoId);
        User user = userRepo.findOne(userId);
        user.addTodo(todo, false);
    }
    
    @Override
    public void removeTodoFromUser(Long userId, Long todoId) {
        Todo todo = todoRepo.findOne(todoId);
        User user = userRepo.findOne(userId);
        user.removeTodo(todo, false);
    }

    @Override
    public void addTodoToCareerLevel(Long careerLevelId, Long todoId) {
        Todo todo = todoRepo.findOne(todoId);
        CareerLevel careerLevel = careerLevelRepo.findOne(careerLevelId);
        careerLevel.addTodo(todo);
    }

    @Override
    public void removeTodoFromCareerLevel(Long careerLevelId, Long todoId) {
        Todo todo = todoRepo.findOne(todoId);
        CareerLevel careerLevel = careerLevelRepo.findOne(careerLevelId);
        careerLevel.removeTodo(todo);
    }

    @Override
    public List<String> findTodosFromLevel(Long id) {
        return this.todoRepo.findTodosFromLevel(id);
    }
}
