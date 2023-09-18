package com.cloud.rest.webservices.webapp.controllers;

import com.timgroup.statsd.StatsDClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HealthController {

    private final static Logger LOGGER = LoggerFactory.getLogger(HealthController.class);

    @Autowired
    private StatsDClient metricsClient;

    @GetMapping(path = "/health")
    public void HealthCheck(){
        LOGGER.info("Health is good");
        long startTime = System.currentTimeMillis();
        metricsClient.incrementCounter("endpoint/healthz/http/get");
        metricsClient.recordExecutionTime("endpoint/healthz/http/get",startTime);
    }
}
