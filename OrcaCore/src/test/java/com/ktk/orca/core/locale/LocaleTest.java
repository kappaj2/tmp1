package com.ktk.orca.core.locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.assertEquals;


@SpringBootTest( )
@RunWith(SpringRunner.class)
@Profile("test")
public class LocaleTest {

    @Autowired
    private MessageSource messageSource;

    @Test
    public void testLocaleResolving(){

        String abc = messageSource.getMessage("ktk.api.rest.exception", null, new Locale("en", "US"));
        assertEquals("Exception calling service API", abc);
    }
}
