package com.ktk.orca.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("!test")
public class StartupInformationSetup implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("Doing commandlineRunner method");
    }

}