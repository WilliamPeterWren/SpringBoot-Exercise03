package com.tranxuanphong.exercise03.service;

import java.util.List;
import java.util.UUID;

import com.tranxuanphong.exercise03.entity.Role;

public interface RoleService {
    // List<Role> findRolesByRolesId(UUID roleId);

    public Role createRole(Role role);

    Role getRoleById(UUID roleId);

    List<Role> getAllRoles();

    Role updateRole(Role role);

    void deleteRole(UUID roleId);
}
