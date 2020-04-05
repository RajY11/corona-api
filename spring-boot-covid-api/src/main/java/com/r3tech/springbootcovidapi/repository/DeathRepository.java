package com.r3tech.springbootcovidapi.repository;

import com.r3tech.springbootcovidapi.models.DeathData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface DeathRepository extends JpaRepository<DeathData,Integer> {
    @Transactional
    @Modifying
    @Query(
            value = "truncate table death_data",
            nativeQuery = true
    )
    void truncateMyTable();
}
