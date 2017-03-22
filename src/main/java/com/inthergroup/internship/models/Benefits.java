package com.inthergroup.internship.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * An entity Benefits composed by two fields (id, benefitName). The Entity
 * annotation indicates that this class is a JPA entity. The Table annotation
 * specifies the name for the table in the db.
 *
 * @author interns
 */
@Entity
@Table(name = "benefits")
public class Benefits {

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    // An autogenerated id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String benefitName;
    
    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    public Benefits() {
    }

    public Benefits(long id) {
        this.id = id;
    }

    public Benefits(long id, String benefitName) {
        this.id = id;
        this.benefitName = benefitName;
    }
    
    // Getter and setter methods

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBenefitName() {
        return benefitName;
    }

    public void setBenefitName(String benefitName) {
        this.benefitName = benefitName;
    }
}
