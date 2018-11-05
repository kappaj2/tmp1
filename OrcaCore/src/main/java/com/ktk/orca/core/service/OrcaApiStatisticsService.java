package com.ktk.orca.core.service;

import com.ktk.orca.core.internalpojos.ApiStatistics;

import javax.validation.constraints.NotNull;

public interface OrcaApiStatisticsService {

    void logApiCall(@NotNull ApiStatistics apiStatistics);
}
