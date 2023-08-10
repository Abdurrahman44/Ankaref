package com.example.ankaref.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    @Column(name="Event_Name")
    private String  eventName;

    @Column(name="Date")
    private String date;
    @Column(name="Description")
    private String explain;
    @ManyToMany
    Set<Users> JoinUser;

}
