package com.ktk.orca.core.service.integration;

import com.ktk.orca.core.IntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
@Category(IntegrationTest.class)
public class TestAccountServiceIntegrationTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void readAccountService() throws IOException {

    }
}
