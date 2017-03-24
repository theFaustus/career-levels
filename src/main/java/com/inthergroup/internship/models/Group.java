package com.inthergroup.internship.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * An entity Groups composed by two fields (id, group_name). The Entity
 * annotation indicates that this class is a JPA entity. The Table annotation
 * specifies the name for the table in the db.
 *
 * @author interns
 */
@Entity
@Table(name = "groups")
public class Group {

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    // An autogenerated id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String groupName;

    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    public Group() {
    }

    public Group(long id) {
        super();
        this.id = id;
    }

    public Group(long id, String groupName) {
        super();
        this.id = id;
        this.groupName = groupName;
    }
    
    // Getter and setter methods

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

} // class Groups