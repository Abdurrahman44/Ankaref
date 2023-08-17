package com.example.ankaref.DataAccess;

import com.example.ankaref.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {//Evet olduğu Data yapılacak işlemlerin olduğu kısım

}
