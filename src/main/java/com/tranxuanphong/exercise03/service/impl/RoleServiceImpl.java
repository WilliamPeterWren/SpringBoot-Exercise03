package com.tranxuanphong.exercise03.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tranxuanphong.exercise03.entity.Role;
import com.tranxuanphong.exercise03.repository.RoleRepository;
import com.tranxuanphong.exercise03.service.RoleService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    // @Override
    // public List<Role> findRolesByRolesId(UUID roleId) {
    // return roleRepository.findRolesByRoleId(roleId);
    // }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleById(UUID roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role updateRole(Role role) {
        if (!roleRepository.existsById(role.getId())) {
            throw new RuntimeException("Role not found with id: " + role.getId());
        }

        Role existingRole = roleRepository.findById(role.getId()).get();

        existingRole.setPrivileges(role.getPrivileges());
        existingRole.setRoleName(role.getRoleName());

        return roleRepository.save(existingRole);
    }

    @Override
    public void deleteRole(UUID roleId) {
        if (!roleRepository.existsById(roleId)) {
            throw new RuntimeException("Role not found with id: " + roleId);
        }
        roleRepository.deleteById(roleId);
    }
}
