package com.stealthmode.service.pig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
//@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard
public class PigController {

    @Autowired
    private EurekaClient client;

    @Autowired
    RestTemplate restTemplate;

    @Value("${hello.pig}")
    private String message;

    @RequestMapping("/msg")
    public String getMessage(){
        return this.message;
    }

    @RequestMapping("/migip")
    @HystrixCommand(fallbackMethod = "getPIGFallbackIPMessage" ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "500")
    })
    public String getPIGMessage(@RequestParam long time) throws InterruptedException{
        Thread.sleep(time);  // Mocking server busy for time amount in ms.
        InstanceInfo val = client.getNextServerFromEureka("mig", false);
        return val.getIPAddr()+":"+val.getPort();
    }
    public String getPIGFallbackIPMessage(long time){
        return "MIG:Down - Default localhost !!";
    }

    @RequestMapping("/migmsg")
    @HystrixCommand(fallbackMethod = "getPIGFallbackMessage")
    public String getClientMessage(){
        String val = restTemplate.getForObject("http://mig:9099/msg", String.class);
        return val;
    }
    public String getPIGFallbackMessage(){
        return "MIG:Down - Default Message !!";
    }


}
