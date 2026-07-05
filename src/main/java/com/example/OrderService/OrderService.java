package com.example.OrderService;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    @Autowired
    ProductClient productClient;

//    @RateLimiter(name="productRateLimiter",
//            fallbackMethod = "rateLimitedFallback")
    /*
 Internally it uses AOP functionality, that’s why
 the method on which this @RateLimiter annotation applies,
 need to be public and Spring managed bean.
 */
@Bulkhead(name="bulkRateLimiter",type = Bulkhead.Type.SEMAPHORE,
        fallbackMethod = "bulkheadLimitedFallback")
    public void invokeProductApi(String id) throws InterruptedException {
    System.out.println("Thread : " + Thread.currentThread().getName());

    // Simulate a long-running request
    Thread.sleep(10000);

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

    public void bulkheadLimitedFallback(String id, Throwable t) {
        System.out.println("Too many requests. Try later");
        // throw exception here and handle it gracefully
    }
}
