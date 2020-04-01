package com.r3tech.springbootcovidapi.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Subselect("select DISTINCT country1,confirmed,death,recovered from country_view order by country1")
public class CoronaDetails {
    @Id
    private String country1;
   // private String state;
    private Integer confirmed;
    private Integer death;
    private Integer recovered;

}
