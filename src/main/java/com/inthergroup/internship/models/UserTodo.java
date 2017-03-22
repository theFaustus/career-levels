package com.inthergroup.internship.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * An entity UserTodo composed by two fields (id, benefitName). The Entity
 * annotation indicates that this class is a JPA entity. The Table annotation
 * specifies the name for the table in the db.
 *
 * @author interns
 */
@Entity
@Table(name = "user_todo")
public class UserTodo {

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    // An autogenerated id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long userId;
    private long todoId;
    
    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    public UserTodo() {
    }

    public UserTodo(long id) {
        this.id = id;
    }

    public UserTodo(long id, long userId, long todoId) {
        this.id = id;
        this.userId = userId;
        this.todoId = todoId;
    }
    
    // Getter and setter methods

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTodoId() {
        return todoId;
    }

    public void setTodoId(long todoId) {
        this.todoId = todoId;
    }
}
