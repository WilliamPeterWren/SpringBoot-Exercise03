package com.tranxuanphong.exercise03.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tranxuanphong.exercise03.entity.StaffAccount;
import com.tranxuanphong.exercise03.repository.StaffAccountRepository;
import com.tranxuanphong.exercise03.service.StaffAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StaffAccountServiceImpl implements StaffAccountService {

    private StaffAccountRepository staffAccountRepository;

    @Override
    public StaffAccount createStaffAccount(StaffAccount staffAccount) {

        return staffAccountRepository.save(staffAccount);
    }

    @Override
    public StaffAccount getStaffAccountById(UUID staffAccountId) {
        return staffAccountRepository.findById(staffAccountId)
                .orElseThrow(() -> new RuntimeException("Staff Account not found with id: " + staffAccountId));
    }

    @Override
    public List<StaffAccount> getAllStaffAccounts() {
        return staffAccountRepository.findAll();
    }

    @Override
    public StaffAccount updateStaffAccount(StaffAccount staffAccount) {
        if (!staffAccountRepository.existsById(staffAccount.getId())) {
            throw new RuntimeException("Staff Account not found with id: " + staffAccount.getId());
        }

        StaffAccount existingStaffAccount = staffAccountRepository.findById(staffAccount.getId()).get();

        existingStaffAccount.setRole(staffAccount.getRole());
        existingStaffAccount.setFirst_name(staffAccount.getFirst_name());
        existingStaffAccount.setLast_name(staffAccount.getLast_name());
        existingStaffAccount.setEmail(staffAccount.getEmail());
        existingStaffAccount.setPhone_number(staffAccount.getPhone_number());
        existingStaffAccount.setPassword_hash(staffAccount.getPassword_hash());
        existingStaffAccount.setActive(staffAccount.isActive());
        existingStaffAccount.setImage(staffAccount.getImage());
        existingStaffAccount.setPlaceholder(staffAccount.getPlaceholder());
        existingStaffAccount.setUpdated_at(staffAccount.getUpdated_at());
        existingStaffAccount.setSubUpdatedBy(staffAccount.getSubUpdatedBy());
        existingStaffAccount.setSubUpdatedBy(staffAccount.getSubUpdatedBy());

        return staffAccountRepository.save(existingStaffAccount);
    }

    @Override
    public void deleteStaffAccount(UUID staffAccountId) {
        if (!staffAccountRepository.existsById(staffAccountId)) {
            throw new RuntimeException("Staff Account not found with id: " + staffAccountId);
        }
        staffAccountRepository.deleteById(staffAccountId);
    }
}
