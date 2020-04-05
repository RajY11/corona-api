package com.r3tech.springbootcovidapi.repository;

import com.r3tech.springbootcovidapi.models.RecoveryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface RecoveryRepository extends JpaRepository<RecoveryData,Integer> {
    @Transactional
    @Modifying
    @Query(
            value = "truncate table recovery_data",
            nativeQuery = true
    )
    void truncateMyTable();
}
