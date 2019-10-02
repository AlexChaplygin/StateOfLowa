package com.alex.che.stateoflowa.repository;

import com.alex.che.stateoflowa.entity.Voter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {

    @Query("SELECT v FROM Voter v WHERE (:county is null or v.county = :county) " +
            "and (:month is null or v.month = :month)"
    )
    List<Voter> getVotersByParams(@Param("county") String county,
                                  @Param("month") Integer month,
                                  Pageable pageable);
}
