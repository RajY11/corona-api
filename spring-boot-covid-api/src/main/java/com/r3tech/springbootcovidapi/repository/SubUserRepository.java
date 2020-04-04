package com.r3tech.springbootcovidapi.repository;

import com.r3tech.springbootcovidapi.models.SubUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubUserRepository extends JpaRepository<SubUser,String> {
}
