package com.example.ankaref.Entities;

import com.example.ankaref.Entities.Users;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "Roles")
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(nullable = false, unique = true)
    private String name;
    @ManyToMany
    Set<Users> Join;


}
