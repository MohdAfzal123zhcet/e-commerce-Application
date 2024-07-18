package com.example.e_commerce.controller;

import com.example.e_commerce.Dto.CustomerReqDto;
import com.example.e_commerce.Dto.CustomerRespDto;

import com.example.e_commerce.Exception.CustomerException;
import com.example.e_commerce.Service.ServiceInterfaceImplementation.CustomerServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity addcustomer(@RequestBody CustomerReqDto customerReqDto)throws CustomerException
    {
          try {
              CustomerRespDto customerRespDto = customerServiceImplementation.addcustomer(customerReqDto);
              return new ResponseEntity<>(customerRespDto,HttpStatus.CREATED);
          }
          catch(Exception e)
          {
              return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
          }


    }
}
