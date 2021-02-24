package com.stock_management.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOCTOR_ID")
    private Long doctorId;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "TELEPHONE_NUMBER")
    private Integer telephoneNumber;

    @CreatedBy
    @ManyToOne(targetEntity = UserProfile.class)
    @JoinColumn(name = "CREATED_BY", referencedColumnName = "USER_ID")
    private UserProfile createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @LastModifiedBy
    @ManyToOne(targetEntity = UserProfile.class)
    @JoinColumn(name = "LAST_MODIFIED_BY", referencedColumnName = "USER_ID")
    private UserProfile lastModifiedBy;

    @LastModifiedDate
    @Column(name = "LAST_Modified_DATE")
    private LocalDateTime lastModifiedDate;

    @PrePersist
    protected void prePersist() {
        createdDate = LocalDateTime.now();
        lastModifiedDate = LocalDateTime.now();
        lastModifiedBy = createdBy;
    }

    @PreUpdate
    protected void preUpdate() {
        lastModifiedDate = LocalDateTime.now();
    }
}
