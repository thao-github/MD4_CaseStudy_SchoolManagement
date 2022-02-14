package com.security.repository;

import com.security.model.Tuition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITuitionRepo extends JpaRepository<Tuition, Long> {
    @Query (nativeQuery = true, value="select * from tuition where user_id = :id")
    List<Tuition> getTuitionHistory(@Param("id") Long id);
}
