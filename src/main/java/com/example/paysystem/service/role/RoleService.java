package com.example.paysystem.service.role;

import com.example.paysystem.entity.Role;
import com.example.paysystem.entity.User;

import java.util.Collection;

public interface RoleService {

    Collection<Role> getAllRoles(User user);
}
