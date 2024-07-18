package com.example.e_commerce.Service.ServiceInterfaceImplementation;

import com.example.e_commerce.Dto.CustomerReqDto;
import com.example.e_commerce.Dto.CustomerRespDto;
import com.example.e_commerce.Repository.CustomerRepository;
import com.example.e_commerce.Service.ServiceInterface.CustomerServiceInterface;
import com.example.e_commerce.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImplementation implements CustomerServiceInterface {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerRespDto addcustomer(CustomerReqDto customerReqDto)
    {
       Customer customer;
       customer=customerRepository.findByemailId(customerReqDto.getEmailId());
       CustomerRespDto customerRespDto=new CustomerRespDto();
       customerRespDto.setName(customer.getName());
        customerRespDto.setMessage("Customer Added");
        customerRepository.save(customer);
        return customerRespDto;
    }
}
