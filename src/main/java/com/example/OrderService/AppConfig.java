package com.example.OrderService;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

//     @Bean
//    public RestTemplate restTemplate(){
//    return new RestTemplate();
//}
//
//    @Bean
//    public RestClient restClientInstance(){
//         return RestClient.create();
////         internally restclinet.builder.build;
//    }

//    for load balancing
    @Bean
    @LoadBalanced
    public RestTemplate restClientInstance(){
        return new RestTemplate();
//         internally restclinet.builder.build;
    }

}
