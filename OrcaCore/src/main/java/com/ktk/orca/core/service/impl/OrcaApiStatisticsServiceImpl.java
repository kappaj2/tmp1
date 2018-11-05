package com.ktk.orca.core.service.impl;

import com.ktk.orca.core.internalpojos.ApiStatistics;
import com.ktk.orca.core.service.OrcaApiStatisticsService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrcaApiStatisticsServiceImpl implements OrcaApiStatisticsService {

    private JmsTemplate jmsTemplate;

    public OrcaApiStatisticsServiceImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Log the statistics for an API call to the database. Use async call to JMS to minimize impact on the API response times.
     *
     * @param apiStatistics
     */
    @Override
    public void logApiCall(ApiStatistics apiStatistics) {
        jmsTemplate.convertAndSend("api-statistics", apiStatistics);
    }
}
