package com.security.service;

import com.security.model.Role;
import com.security.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
