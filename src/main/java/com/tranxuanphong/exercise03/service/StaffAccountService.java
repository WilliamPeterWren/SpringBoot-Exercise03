package com.tranxuanphong.exercise03.service;

import java.util.List;
import java.util.UUID;

import com.tranxuanphong.exercise03.entity.StaffAccount;

public interface StaffAccountService {
    public StaffAccount createStaffAccount(StaffAccount staffAccount);

    StaffAccount getStaffAccountById(UUID staffAccountId);

    List<StaffAccount> getAllStaffAccounts();

    StaffAccount updateStaffAccount(StaffAccount staffAccount);

    void deleteStaffAccount(UUID staffAccountId);
}
