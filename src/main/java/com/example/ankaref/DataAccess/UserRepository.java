package com.example.ankaref.DataAccess;

import com.example.ankaref.Entities.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
  public Users findByEmail(String email );//Kullanıcıların olduğu Data yapılacak işlemlerin olduğu kısım



}
