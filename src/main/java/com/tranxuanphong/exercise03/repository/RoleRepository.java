package com.tranxuanphong.exercise03.repository;

// import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tranxuanphong.exercise03.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    // List<Role> findRolesByRoleId(UUID roleId);
}
