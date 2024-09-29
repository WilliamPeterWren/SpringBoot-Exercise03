package com.tranxuanphong.exercise03.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranxuanphong.exercise03.entity.StaffAccount;
import com.tranxuanphong.exercise03.service.StaffAccountService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@AllArgsConstructor
@RequestMapping("api/staffAccounts")
public class StaffAccountController {
    @Autowired
    private StaffAccountService staffAccountService;

    @PostMapping
    public ResponseEntity<StaffAccount> createStaffAccount(@RequestBody StaffAccount staffAccount) {
        StaffAccount savedStaffAccount = staffAccountService.createStaffAccount(staffAccount);
        return new ResponseEntity<>(savedStaffAccount, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<StaffAccount> getStaffAccountById(@PathVariable("id") UUID staffAccountId) {
        StaffAccount staffAccount = staffAccountService.getStaffAccountById(staffAccountId);
        return new ResponseEntity<>(staffAccount, HttpStatus.OK);
    }

    // Get All StaffAccounts REST API
    // http://localhost:8080/api/StaffAccounts
    @GetMapping
    public ResponseEntity<List<StaffAccount>> getAllStaffAccounts() {
        List<StaffAccount> StaffAccounts = staffAccountService.getAllStaffAccounts();
        return new ResponseEntity<>(StaffAccounts, HttpStatus.OK);
    }

    // Update StaffAccount REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/StaffAccounts/1
    public ResponseEntity<StaffAccount> updateStaffAccount(@PathVariable("id") UUID staffAccountId,
            @RequestBody StaffAccount staffAccount) {
        staffAccount.setId(staffAccountId);
        StaffAccount updatedStaffAccount = staffAccountService.updateStaffAccount(staffAccount);
        return new ResponseEntity<>(updatedStaffAccount, HttpStatus.OK);
    }

    // Delete StaffAccount REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStaffAccount(@PathVariable("id") UUID staffAccountId) {
        staffAccountService.deleteStaffAccount(staffAccountId);
        return new ResponseEntity<>("StaffAccount successfully deleted!", HttpStatus.OK);
    }
}
