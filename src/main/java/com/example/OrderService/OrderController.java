package com.example.OrderService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    RestTemplate restTemplate;
//
//    @Autowired
//    RestClient restClient;

    @Autowired
    OrderService orderService;

    @Autowired
    DiscoveryClient discoveryClient;

    @Value("${product.service.baseurl}")
    String productBaseUrl;

//with rest template

//    @GetMapping("/{id}")
//    public ResponseEntity<String> getOrder(@PathVariable String id){
//        //invoke product api
//        String response=restTemplate.getForObject(
//                "http://localhost:8085/products/"+id,String.class);
//        System.out.println("response from product api called from order service "+response);
//
//
//        return ResponseEntity.ok("order call successfully");
//    }
//

//    with restclient

//@GetMapping("/{id}")
//public ResponseEntity<String> getOrder(@PathVariable String id){
//    //invoke product api
//
//    String response=restClient.get().uri("http://localhost:8085/products/"+id)
//            .retrieve().body(String.class);
//
//
//    System.out.println("response from product api called from order service "+response);
//
//
//    return ResponseEntity.ok("order call successfully");
//}

//with service discovery

//    @GetMapping("/{id}")
//    public ResponseEntity<String> getOrder(@PathVariable String id){
//        //invoke product api
//
//        List<ServiceInstance> instances=discoveryClient.getInstances("product-service");
//        URI uri=instances.get(0).getUri();
//        String response=restTemplate.getForObject(uri+"/products/"+id,String.class);
//        System.out.println("response from product api called from order service "+response);
//
//        return ResponseEntity.ok("order call successfully");
//    }

//  load balancer
//
//@GetMapping("/{id}")
//public void callProductApi(@PathVariable String id){
//    String response=restTemplate.getForObject(productBaseUrl+"/products/"+id,String.class);
//    System.out.println("Response from product api is "+response);
//}

//    rate limiter
//    for bulhead also same

    @GetMapping("/{id}")
    public void callProductApi(@PathVariable String id) throws InterruptedException {
        orderService.invokeProductApi(id);
    }
}
