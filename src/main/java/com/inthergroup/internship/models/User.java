package com.inthergroup.internship.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * An entity User composed by three fields (id, email, name).
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
    
    // An autogenerated id (unique for each user in the db)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 20)
    private long id;
    
    // The user's last name
    @Column(length = 64)
    private String lastName;
    
    // The user's first name
    @Column(length = 64)
    private String firstName;
    
    // The user's login
    @Column(nullable = false, length = 64, unique = true)
    private String login;
    
    // The user's password
    @Column(length = 64)
    private String password;
    
    // The user's email
    @Column(length = 64, unique = true)
    private String email;
    
    // The user's career level
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="career_level_id", nullable = false)
    private CareerLevel careerLevel;
    
    // The user's benefits
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_benefits",
            joinColumns=@JoinColumn(name="user_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="benefit_id", referencedColumnName="id"))
    private List<Benefit> benefits;
    
    // The user's todos
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_todos",
            joinColumns=@JoinColumn(name="user_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="todo_id", referencedColumnName="id"))
    private List<Todo> todos;
    
 // The user's groups
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_groups",
            joinColumns=@JoinColumn(name="user_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="group_id", referencedColumnName="id"))
    private List<Group> groups;

    // ------------------------
    // PUBLIC METHODS
    // ------------------------
    
    public User() { }
    
    public User(long id) {
        this.id = id;
    }
    
    public User(String lastName, String firstName, String login,
                String password, String email, CareerLevel careerLevel) {
        super();
        this.lastName = lastName;
        this.firstName = firstName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.careerLevel = careerLevel;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public List<Benefit> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<Benefit> benefits) {
        this.benefits = benefits;
    }
    
    public void addBenefit(Benefit benefit) {        
        benefits.add(benefit);
        
        if (!benefit.getUsers().contains(this)) { // warning this may cause performance issues if you have a large data set since this operation is O(n)
            benefit.getUsers().add(this);
        }
    }
    
    public void removeBenefit(Benefit benefit) {        
        benefits.remove(benefit);
        
        if (benefit.getUsers().contains(this)) { // warning this may cause performance issues if you have a large data set since this operation is O(n)
            benefit.getUsers().remove(this);
        }
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
    
    public void addTodo(Todo todo) {        
        todos.add(todo);
        
        if (!todo.getUsers().contains(this)) { // warning this may cause performance issues if you have a large data set since this operation is O(n)
            todo.getUsers().add(this);
        }
    }
    
    public void removeTodo(Todo todo) {        
        todos.remove(todo);
        
        if (todo.getUsers().contains(this)) { // warning this may cause performance issues if you have a large data set since this operation is O(n)
            todo.getUsers().remove(this);
        }
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
    
    public void addGroup(Group group) {        
        groups.add(group);
        
        if (!group.getUsers().contains(this)) { // warning this may cause performance issues if you have a large data set since this operation is O(n)
            group.getUsers().add(this);
        }
    }
    
    public void removeGroup(Group group) {        
        groups.remove(group);
        
        if (group.getUsers().contains(this)) { // warning this may cause performance issues if you have a large data set since this operation is O(n)
            group.getUsers().remove(this);
        }
    }
    
} // class User
