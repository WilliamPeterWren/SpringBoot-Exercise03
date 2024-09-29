package com.tranxuanphong.exercise03.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tranxuanphong.exercise03.entity.StaffAccount;

public interface StaffAccountRepository extends JpaRepository<StaffAccount, UUID> {
    @Query("SELECT DISTINCT s FROM StaffAccount s LEFT JOIN FETCH s.subCreatedBy WHERE s.createdBy IS NULL")
    List<StaffAccount> findAllWithSubCategories();
}
