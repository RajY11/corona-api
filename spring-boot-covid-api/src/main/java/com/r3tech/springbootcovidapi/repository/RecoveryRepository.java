package com.r3tech.springbootcovidapi.repository;

import com.r3tech.springbootcovidapi.models.RecoveryData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecoveryRepository extends JpaRepository<RecoveryData,Integer> {
}
