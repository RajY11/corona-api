package com.r3tech.springbootcovidapi.repository;

import com.r3tech.springbootcovidapi.models.CoronaData;
import com.r3tech.springbootcovidapi.models.CoronaDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoronaRepository extends JpaRepository<CoronaData,Integer> {

    @Query(value="select * from corono_full_data",nativeQuery = true)
    List<Object> findAllCoronaDetails();
}
