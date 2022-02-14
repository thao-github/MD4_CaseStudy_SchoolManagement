package com.security.repository;

import com.security.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String name); //Tim xem user co trong DB k?

    Boolean existsByUsername(String username); //check username da ton tai trong DB chua?

    Boolean existsByEmail(String email); //check xem email ton tai trong DB chua?

    @Query(nativeQuery = true, value = "select * from (select * from user_role join users on user_role.user_id = users.id where role_id = 2) as coach limit :start , 3")
    List<User> findAllCoach(@Param("start") int start);

    @Query(nativeQuery = true, value = "select * from (select * from user_role join users on user_role.user_id = users.id where role_id = 2) as coach where name like concat ('%', :name, '%') and limit :start , 3")
    List<User> searchCoach(@Param("name") String name, @Param("start") int start);


    @Query(nativeQuery = true, value = "select count(id) from(select * from (select * from user_role join users on user_role.user_id = users.id where role_id = 2) as coach)as count_coach")
    int countCoaches();
}
