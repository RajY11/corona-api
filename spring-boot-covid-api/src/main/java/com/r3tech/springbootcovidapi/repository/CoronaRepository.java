package com.r3tech.springbootcovidapi.repository;

import com.r3tech.springbootcovidapi.models.CoronaData;
import com.r3tech.springbootcovidapi.models.CoronaDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CoronaRepository extends JpaRepository<CoronaData,Integer> {

    @Query(value="select * from corono_full_data",nativeQuery = true)
    List<Object> findAllCoronaDetails();
    @Transactional
    @Modifying
    @Query(
            value = "truncate table corona_data",
            nativeQuery = true
    )
    void truncateMyTable();
}
