package com.stealthmode.service.mig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class MigController {

    @Autowired
    private EurekaClient client;

    @Autowired
    RestTemplate restTemplate;

    @Value("${hello.mig}")
    private String message;

    @RequestMapping("/msg")
    public String getMessage(){
        return this.message;
    }

    @RequestMapping("/pigip")
    public String getPIGMessage(){
        InstanceInfo val = client.getNextServerFromEureka("pig", false);
        return val.getIPAddr()+":"+val.getPort();
    }

    @RequestMapping("/pigmsg")
    public String getClientMessage(){
        String val = restTemplate.getForObject("http://pig:9098/msg", String.class);
        return val;
    }

}
