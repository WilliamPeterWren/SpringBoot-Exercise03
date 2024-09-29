package com.tranxuanphong.exercise03.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tranxuanphong.exercise03.repository.RoleRepository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "staff_accounts")
public class StaffAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    @JsonBackReference
    private Role role;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String first_name;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String last_name;

    @Column(nullable = true, columnDefinition = "VARCHAR(100)")
    private String phone_number;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    private String email;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String password_hash;

    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean active;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String image;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String placeholder;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    @PrePersist
    protected void onCreate() {
        Date now = new Date();
        created_at = now;
        updated_at = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = new Date();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @JsonBackReference
    private StaffAccount createdBy;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<StaffAccount> subCreatedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    @JsonBackReference
    private StaffAccount updatedBy;

    @OneToMany(mappedBy = "updatedBy", cascade = CascadeType.ALL)
    private List<StaffAccount> subUpdatedBy;

    public void setRoleById(UUID roleId, RoleRepository roleRepository) {
        this.role = roleRepository.findById(roleId).orElse(null);
    }

}
