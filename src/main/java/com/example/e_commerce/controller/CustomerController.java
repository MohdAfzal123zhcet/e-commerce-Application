package com.example.e_commerce.controller;

import com.example.e_commerce.Dto.CustomerReqDto;
import com.example.e_commerce.Dto.CustomerRespDto;

import com.example.e_commerce.Service.ServiceInterfaceImplementation.CustomerServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerServiceImplementation customerServiceImplementation;
    @PostMapping("/add")
    public CustomerRespDto addcustomer(@RequestBody CustomerReqDto customerReqDto)
    {
       return customerServiceImplementation.addcustomer(customerReqDto);
    }
}
