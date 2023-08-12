package com.example.ankaref.DataAccess;

import com.example.ankaref.Entities.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
   Optional<Users> findByEmail(String email );//Kullanıcıların olduğu Data yapılacak işlemlerin olduğu kısım


}
