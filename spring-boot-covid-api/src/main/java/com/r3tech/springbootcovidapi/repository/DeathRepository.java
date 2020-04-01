package com.r3tech.springbootcovidapi.repository;

import com.r3tech.springbootcovidapi.models.DeathData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeathRepository extends JpaRepository<DeathData,Integer> {
}
