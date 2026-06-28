package com.example.OrderService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    @Autowired
    ProductClient productClient;

    @RateLimiter(name="productRateLimiter",
            fallbackMethod = "rateLimitedFallback")
    /*
 Internally it uses AOP functionality, that’s why
 the method on which this @RateLimiter annotation applies,
 need to be public and Spring managed bean.
 */
    public void invokeProductApi(String id){
        String response=productClient.getProductById(id);
        System.out.println("Response from Product api call is"+response);
    }

    /*
 Fallback method, return type and parameter
 (additionally only Throwable need to add more)
 should match with the original method, else default
 fallback method provided by RateLimiter framework will get
invoked.
 */
public void rateLimitedFallback(String id, Throwable t) {
        System.out.println("Rate limit exceeded. Try later");
        // throw exception here and handle it gracefully
}
}
