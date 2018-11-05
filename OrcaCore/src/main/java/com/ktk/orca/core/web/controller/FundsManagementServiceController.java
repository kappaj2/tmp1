package com.ktk.orca.core.web.controller;

import com.ktk.orca.core.service.FundsManagementService;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class FundsManagementServiceController {

    private FundsManagementService fundsManagementService;

    public FundsManagementServiceController(FundsManagementService fundsManagementService) {
        this.fundsManagementService = fundsManagementService;
    }

    @Timed
    @Audited
    @GetMapping("/service/funds/{accountId}")
    public ResponseEntity getAccountService(@PathVariable final String accountId) {
        return null;
    }
}
