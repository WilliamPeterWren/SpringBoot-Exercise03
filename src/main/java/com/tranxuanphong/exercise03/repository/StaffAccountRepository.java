package com.tranxuanphong.exercise03.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tranxuanphong.exercise03.entity.StaffAccount;

public interface StaffAccountRepository extends JpaRepository<StaffAccount, UUID> {

}
