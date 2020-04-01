package com.r3tech.springbootcovidapi.repository;

import com.r3tech.springbootcovidapi.models.CoronaDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoronaDetailsRepository extends JpaRepository<CoronaDetails,Integer> {
}
