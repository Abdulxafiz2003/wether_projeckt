package com.example.demoauth.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "cities")
@Setter
@Getter
public class Cities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String citiesName;
    private String weather;
    private String degree;
    private Boolean status;
    @CreationTimestamp
    private Timestamp creationDate;
}
