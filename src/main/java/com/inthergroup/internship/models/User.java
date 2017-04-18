package com.inthergroup.internship.models;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * An entity User composed by eight fields (id, lastName, firstName, username,
 * password, email, careerLevel, group).
 * The Entity annotation indicates that this class is a JPA entity.
 * The Table annotation specifies the name for the table in the db.
 *
 * @author interns
 */
@Entity
@Table(name = "users")
public class User {

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------
    
    /**
     *  An autogenerated id (unique for each user in the db)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 20)
    private long id;
    
    /**
     *  The user's last name
     */
    @Column(nullable = false, length = 64)
    private String lastName;
    
    /**
     *  The user's first name
     */
    @Column(nullable = false, length = 64)
    private String firstName;
    
    /**
     *  The user's username
     */
    @Column(nullable = false, length = 64, unique = true)
    private String username;
    
    /**
     *  The user's password
     */
    @Column(length = 64)
    private String password;
    
    /**
     *  The user's email
     */
    @Column(nullable = false, length = 64, unique = true)
    private String email;
    
    /**
     *  The user's career level
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="career_level_id", nullable = false)
    private CareerLevel careerLevel;
    
    /**
     *  The user's group
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
    
    /**
     *  The user's todos
     */
    @OneToMany(mappedBy = "primaryKey.user", orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<Todo> todos = new HashSet<Todo>();

    // ------------------------
    // PUBLIC METHODS
    // ------------------------
    
    public User() { }
    
    public User(long id) {
        this.id = id;
    }
    
    public User(String lastName, String firstName, String username, String password,
            String email, CareerLevel careerLevel, Group group) {
        super();
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.careerLevel = careerLevel;
        this.group = group;
    }
    
    // Getter and setter methods

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CareerLevel getCareerLevel() {
        return careerLevel;
    }

    public void setCareerLevel(CareerLevel careerLevel) {
        if (this.careerLevel.getUsers().contains(this)) {
            this.careerLevel.getUsers().remove(this);
        }
        
        this.careerLevel = careerLevel;
        if (!careerLevel.getUsers().contains(this)) { // warning this may cause performance issues if you have a large data set since this operation is O(n)
            careerLevel.getUsers().add(this); 
        }
    }
    
    /**
     * Add a todo to the user.
     * Create an association object for the relationship and set its data.
     *  
     * @param todoId
     * @param todoTypeId
     * @param dateOfCompletion
     * @param description
     */
    public void addTodo(String todoId, long todoTypeId, Date dateOfCompletion,
            String description) {
        Todo todo = new Todo();
        todo.setUser(this);
        todo.setCareerLevel(this.careerLevel);
        todo.setTodoId(todoId);
        todo.setTodoTypeId(todoTypeId);
        todo.setDateOfCompletion(dateOfCompletion);
        todo.setDescription(description);
        todos.add(todo);
    }
    
    /**
     * Remove a todo from the user.
     * Create an association object for the relationship and set its data.
     * 
     * @param todoId
     * @param careerLevel
     */
    public void removeTodo(String todoId, CareerLevel careerLevel) {
        Todo todo = new Todo();
        todo.setUser(this);
        todo.setCareerLevel(careerLevel);
        todo.setTodoId(todoId);
        todos.remove(todo);
    }

    public Set<Todo> getTodos() {
        return todos;
    }

    public void setTodos(Set<Todo> userTodos) {
        this.todos = userTodos;
    }
    
    public Group getGroup() {
        return group;
    }
    
    public void setGroup(Group group) {
        if (this.group.getUsers().contains(this)) {
            this.group.getUsers().remove(this);
        }
        
        this.group = group;
        if (!group.getUsers().contains(this)) { // warning this may cause performance issues if you have a large data set since this operation is O(n)
            group.getUsers().add(this); 
        }
    }
} // class User
