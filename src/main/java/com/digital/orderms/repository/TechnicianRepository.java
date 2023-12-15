package com.digital.orderms.repository;

import com.digital.orderms.domain.ExpertTechnician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TechnicianRepository extends JpaRepository<ExpertTechnician, Long> {

    @Transactional
    @Modifying
    @Query("update ExpertTechnician ex set ex.isActive = :status where ex.id = :id")
    void updateStatus(@Param(value = "status") Boolean status,
                      @Param(value = "id") Long id);

    @Query("SELECT ex " +
            "FROM ExpertTechnician  AS ex " +
            "LEFT JOIN Address AS a " +
            "ON  ex.personalAddress.id = a.id " +
            "LEFT JOIN BaseAddress AS ba " +
            "ON ba.id = a.baseAddress.id " +
            "WHERE ba.id like :id " +
            "AND ex.isActive = true ")
    List<ExpertTechnician> findByBaseAddress(@Param(value = "id") String id);
}