package com.enil.haberusers.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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