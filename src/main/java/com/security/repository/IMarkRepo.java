package com.security.repository;

import com.security.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMarkRepo extends JpaRepository<Mark, Long> {
    @Query(nativeQuery = true, value ="select * from users join mark on users.id = mark.user_id where user_id = :id")
    List<Mark> getStudentMark(@Param("id") Long id);
}
