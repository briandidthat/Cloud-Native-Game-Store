package com.trilogyed.retailapi.util.feign;

import com.trilogyed.retailapi.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "customer-service")
@RequestMapping(value = "/customers")
public interface CustomerClient {
    @PostMapping
    Customer createCustomer(@RequestBody @Valid Customer customer);

    @GetMapping(value = "/{customerId}")
    Customer getCustomer(@PathVariable int customerId);

}
