package com.r3tech.springbootcovidapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "emailId")})

public class SubUser {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int Id;
    private String emailId;
    @ElementCollection
    private List<String> countries = new ArrayList<>();

    public SubUser(){}

    public SubUser(int id, String emailId, List<String> countries) {
        Id = id;
        this.emailId = emailId;
        this.countries = countries;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }
}
