package com.tranxuanphong.exercise03.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranxuanphong.exercise03.entity.Role;
import com.tranxuanphong.exercise03.service.RoleService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@AllArgsConstructor
@RequestMapping("api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role savedRole = roleService.createRole(role);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") UUID roleId) {
        Role role = roleService.getRoleById(roleId);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    // Get All Roles REST API
    // http://localhost:8080/api/Roles
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> Roles = roleService.getAllRoles();
        return new ResponseEntity<>(Roles, HttpStatus.OK);
    }

    // Update Role REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/Roles/1
    public ResponseEntity<Role> updateRole(@PathVariable("id") UUID roleId,
            @RequestBody Role role) {
        role.setId(roleId);
        Role updatedRole = roleService.updateRole(role);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    // Delete Role REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRole(@PathVariable("id") UUID roleId) {
        roleService.deleteRole(roleId);
        return new ResponseEntity<>("Role successfully deleted!", HttpStatus.OK);
    }
}
