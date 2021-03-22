package com.pluralsight.app;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class AppApplication {

    @Autowired
    private EurekaClient client;

    @Autowired
    private RestTemplateBuilder templateBuilder;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }


    @RequestMapping("/")
    public String callService() {
        RestTemplate template = templateBuilder.build();

        // Fetch service url. Non-secure request, so put last param to false
        InstanceInfo instanceInfo = client.getNextServerFromEureka("service", false);
        String baseUrl = instanceInfo.getHomePageUrl();

        // Send the request to the fetched baseUrl
        ResponseEntity<String> response = template.exchange(baseUrl, HttpMethod.GET, null, String.class);
        return response.getBody();
    }

}
