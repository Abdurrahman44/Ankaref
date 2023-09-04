package com.example.ankaref.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "Event_Name")
    private String eventName;

    @Column(name = "Date")
    private LocalDate date;
    @Column(name = "Description")
    private String explain;
    @ManyToMany
    Set<Users> JoinUser;

    public Set<Users> getParticipants() {
        return JoinUser; // JoinUser seti etkinliğe katılan kullanıcıları içeriyor
    }
}
