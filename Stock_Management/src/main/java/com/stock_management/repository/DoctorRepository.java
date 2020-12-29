package com.stock_management.repository;

import com.stock_management.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>, QuerydslPredicateExecutor<Doctor> {
}
