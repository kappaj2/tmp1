package com.ktk.orca.core.repository;

import com.ktk.orca.core.model.OrcaApiStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrcaApiStatisticsRepository extends JpaRepository<OrcaApiStatistics, Long> {
}
