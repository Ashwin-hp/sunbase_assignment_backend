package com.ashkash.sunbase_backend.controller;

import com.ashkash.sunbase_backend.CustomerNotFoundException;
import com.ashkash.sunbase_backend.dto.CustomerDTO;
import com.ashkash.sunbase_backend.entity.Customer;
import com.ashkash.sunbase_backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sunbase/portal/api/assignment")
@CrossOrigin
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/addCustomer")
    public ResponseEntity<CustomerDTO> addUser(@RequestBody CustomerDTO customerDTO){
        CustomerDTO savedCustomer = customerService.addCustomer(customerDTO);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/allCustomers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(){
        List<CustomerDTO> allCustomers=customerService.findAllCustomers();
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable int customerId) {
        Customer customer = customerService.getCustomerById(customerId);

        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int customerId) {
        try {
            customerService.deleteCustomerById(customerId);
            return ResponseEntity.ok("Successfully deleted");
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Not deleted");
        }
    }


    @PutMapping("/{customerId}")
    public ResponseEntity<String> updateCustomer(@PathVariable int customerId, @RequestBody CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request body is empty");
        }

        try {
            customerService.updateCustomerById(customerId, customerDTO);
            return ResponseEntity.ok("Successfully updated");
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ID not found");
        }
    }

}
