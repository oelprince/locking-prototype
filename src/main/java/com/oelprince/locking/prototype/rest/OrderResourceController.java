package com.oelprince.locking.prototype.rest;

import com.oelprince.locking.prototype.entities.Customer;
import com.oelprince.locking.prototype.entities.Order;
import com.oelprince.locking.prototype.repository.OrderRepository;
import com.oelprince.locking.prototype.repository.CustomerRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Slf4j
@RestController
public class OrderResourceController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;


    private SecureRandom secureRandom = new SecureRandom();


    @Setter
    @Getter
    @Builder
    public static class CustomerResponse {
        private Customer customer;

    }

    @Transactional
    @PostMapping(value = "/save-customer", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody Customer customer) {
        long randomLong = secureRandom.nextInt();

        customer.setCustomerNbr(String.valueOf(Math.abs(randomLong)));
        customer.setCrteTs(Timestamp.from(Instant.now()));
        customer.setUpdTs(Timestamp.from(Instant.now()));
        Customer clBillSaved = customerRepository.save(customer);
        log.info("Customer number {} created.", clBillSaved.getCustomerNbr());
        CustomerResponse billResponse = CustomerResponse.builder().customer(clBillSaved).build();
        
        return ResponseEntity.ok(billResponse);
    }


    @Transactional
    @PutMapping(value = "/delete-order/{orderNbr}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomerResponse> deleteCustomer(@PathVariable("orderNbr") String orderNbr) {
        Optional<Order> orderOptionl = orderRepository.findById(orderNbr);

        if(!orderOptionl.isPresent()) {
            return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
        }

        orderRepository.delete(orderOptionl.get());

        Optional<Customer> billOptional = customerRepository.findById(orderOptionl.get().getCustomerNbr());

        Customer clBill = billOptional.get();

        BigDecimal billTotalAmd = clBill.getCustomerTotlAmt().subtract(orderOptionl.get().getEntryAmt());

        clBill.setCustomerTotlAmt(billTotalAmd);

        customerRepository.save(clBill);
        
        
        
        return new ResponseEntity(orderOptionl.get(), HttpStatus.ACCEPTED);
    }




    @Transactional
    @PostMapping(value = "/save-order", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomerResponse> saveOrder(@RequestParam String customerNbr, @RequestBody Order order) {
        long randomLong = secureRandom.nextInt();

        log.info("##### entryNbr {} entryAmt {}",order.getCustomerNbr(), order.getEntryAmt());

        Optional<Customer> customerOption = customerRepository.findById(customerNbr);

        Customer customer = null;

        if(!customerOption.isPresent()) {
            customer = new Customer();
            customer.setCustomerStusCd("CREATE");
            customer.setCustomerFilerCd(customerNbr.substring(0, 3));
            customer.setCustomerNbr(String.valueOf(Math.abs(randomLong)));
            customer.setCustomerTotlAmt(order.getEntryAmt());
            customer.setCrteTs(Timestamp.from(Instant.now()));
            customer.setUpdTs(Timestamp.from(Instant.now()));
            customer.setCrteById("SYS");
            customer.setUpdById("SYS");
        } else {
            customer = customerOption.get();
            BigDecimal totalAmt = customer.getCustomerTotlAmt().add(order.getEntryAmt());
            customer.setCustomerTotlAmt(totalAmt);
        }
        Customer clBillSaved = customerRepository.save(customer);

        long randomLong1 = secureRandom.nextLong();

        order.setOrderNbr(String.valueOf(randomLong1));
        order.setCustomerNbr(customerNbr);
        order.setCrteTs(Timestamp.from(Instant.now()));
        order.setUpdTs(Timestamp.from(Instant.now()));
        order.setCrteById("SYS");
        order.setUpdById("SYS");

        Order orderSaved = orderRepository.save(order);

        log.info("order saved with order nbr = {}", orderSaved.getOrderNbr());

        CustomerResponse customerResponse = CustomerResponse.builder().customer(clBillSaved).build();

        return ResponseEntity.ok(customerResponse);
    }

}
