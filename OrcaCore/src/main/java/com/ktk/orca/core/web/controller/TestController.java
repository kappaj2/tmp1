package com.ktk.orca.core.web.controller;

import com.ktk.orca.core.internalpojos.ApiStatistics;
import com.ktk.orca.core.service.OrcaApiStatisticsService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@Slf4j
@RestController
@RequestMapping("/api/v1")
public class TestController {

    private OrcaApiStatisticsService orcaApiStatisticsService;
    private MessageSource messageSource;

    public TestController(OrcaApiStatisticsService orcaApiStatisticsService,
                          MessageSource messageSource) {
        this.orcaApiStatisticsService = orcaApiStatisticsService;
        this.messageSource = messageSource;
    }

    @Timed
    @Audited
    @GetMapping(value = "/test")
    public ResponseEntity testControllerSetup() {
        log.debug("Testing controller");
        return new ResponseEntity<>("", HttpStatus.OK);
    }


    @GetMapping("/testjms")
    public ResponseEntity testJms() {
        String responseMessage = messageSource.getMessage("ktk.api.success", null, LocaleContextHolder.getLocale());

        ApiStatistics stats = ApiStatistics.builder()
                .apiCalled("TestApi")
                .apiMethodCalled("testMethod")
                .callDuration(3600L)
                .apiRequestTime((new Date()).toInstant())
                .requestPayload("Payload")
                .responseCode(200)
                .responseMessage(responseMessage)
                .responsePayload("ResponsePayload")
                .build();

        orcaApiStatisticsService.logApiCall(stats);
        return new ResponseEntity<>("Submittted to JMS", HttpStatus.OK);
    }
}
