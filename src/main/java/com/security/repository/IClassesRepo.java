package com.security.repository;

import com.security.model.Classes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClassesRepo extends CrudRepository<Classes, Long> {
    @Query(nativeQuery = true, value = "select count(id) from classes")
    Long countClasses();

    @Query(nativeQuery = true, value = "select * from classes where classes.name like concat ('%',:name,'%')")
    List<Classes> findAllByName (@Param("name")String name);


}
