package com.ktk.orca.core.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Configuration
public class ServiceMethodLoggingAOP {

    @Autowired
    @Qualifier("jsonObjectMapper")
    private ObjectMapper objectMapper;

    /**
     * Define the pointcut for the AOP activity.
     */
    @Pointcut("within(com.ktk.orca.core.service.impl..*)")
    private void serviceCallPointcut() {
    }

    /**
     * This AOP will log the parameters that the service class method received, hand control to the method and then log the response object as well.
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("serviceCallPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();

        Map<String, Object> annotatedParameterValue
                = getAnnotatedParameterValue(MethodSignature.class.cast(joinPoint.getSignature()).getMethod(), joinPoint.getArgs(), codeSignature.getParameterNames());

        MDC.clear();
        annotatedParameterValue.entrySet().stream().forEach(stringObjectEntry ->
            MDC.put(stringObjectEntry.getKey(), stringObjectEntry.getValue().toString())
        );

        Instant startTime = Instant.now();

        Object retVal = joinPoint.proceed();

        Instant endTime = Instant.now();
        long callDuration = Duration.between(startTime, endTime).toMillis();

        /*
            Submit log information to Stash.
         */
        MDC.clear();
        MDC.put("ResponsePayload", objectMapper.writeValueAsString(retVal));
        MDC.put("callDuration", String.valueOf(callDuration));
        log.info("Completed call");
        MDC.clear();

        return retVal;
    }

    private Map<String, Object> getAnnotatedParameterValue(Method method, Object[] parameterValues, String[] parameterNames) {

        Map<String, Object> annotatedParameters = new HashMap<>();

        int i = 0;
        for (Annotation[] annotations : method.getParameterAnnotations()) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof DataA) {
                    annotatedParameters.put(parameterNames[i], parameterValues[i]);
                }
                i++;
            }
        }
        return annotatedParameters;
    }

}
