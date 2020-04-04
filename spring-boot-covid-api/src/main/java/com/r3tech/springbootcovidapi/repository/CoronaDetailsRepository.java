package com.r3tech.springbootcovidapi.repository;

import com.r3tech.springbootcovidapi.models.CoronaDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoronaDetailsRepository extends JpaRepository<CoronaDetails,Integer> {
    List<CoronaDetails> findAllByCountry1In(List<String> country1);
}
