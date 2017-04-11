package com.enil.haberservices.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceInstanceRestController {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Value("${spring.application.name:nema}")
    String app;
    @RequestMapping("/info")
    public List<ServiceInstance> serviceInstanceByApplicationName(){
        return this.discoveryClient.getInstances(app);
    }
}