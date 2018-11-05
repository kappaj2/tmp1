package com.ktk.orca.core.service.jms;


import com.ktk.orca.core.internalpojos.ApiStatistics;
import com.ktk.orca.core.model.OrcaApiStatistics;
import com.ktk.orca.core.repository.OrcaApiStatisticsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class StatisticsReceiver {

    private OrcaApiStatisticsRepository orcaApiStatisticsRepository;

    public StatisticsReceiver(OrcaApiStatisticsRepository orcaApiStatisticsRepository) {
        this.orcaApiStatisticsRepository = orcaApiStatisticsRepository;
    }

    /**
     * Receive an ApiStatistics object on the JMS queue, convert to entity and save to the DB.
     *
     * @param apiStatistics
     */
    @JmsListener(destination = "ApiStatistics", containerFactory = "myFactory")
    public void receiveMessage(ApiStatistics apiStatistics) {

        OrcaApiStatistics orcaApiStatistics = new OrcaApiStatistics();

        BeanUtils.copyProperties(apiStatistics, orcaApiStatistics);
        orcaApiStatisticsRepository.save(orcaApiStatistics);
    }
}
