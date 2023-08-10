package com.example.ankaref.DataAccess;

import com.example.ankaref.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    //Role findByroleName(String name);

}

