package com.security.repository;

import com.security.model.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClassRepo extends JpaRepository<Classes, Long> {

}
