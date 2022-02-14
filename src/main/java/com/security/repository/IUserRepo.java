package com.security.repository;

import com.security.model.Classes;
import com.security.model.User;
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

    @Query(nativeQuery = true, value = "select * from (select * from user_role join users on user_role.user_id = users.id where role_id = 3) as student where classes_id = :id")
    List<User> findAllStudentByCoach(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select * from (select * from user_role join users on user_role.user_id = users.id where role_id = 3) as student where classes_id = :id and name like concat ('%',:name,'%')")
    List<User> findAllStudentByName(@Param("id") Long id, @Param("name") String name);

    @Query(nativeQuery = true, value = "select * from (select * from user_role join users on user_role.user_id = users.id where role_id = 3) as student where classes_id = :classId and user_id = :studentId")
    User findUserById(@Param("classId") Long classId, @Param("studentId") Long studentId);

    @Query(nativeQuery = true, value = "select * from (select * from users join user_role on users.id = user_role.user_id where user_role.role_id= 3) as student where classes_id = :id and status = :status")
    List <User> findUserByStatus(@Param("id") Long id, @Param("status") String status);
}
