package com.example.e_commerce.Service.ServiceInterfaceImplementation;

import com.example.e_commerce.Dto.ReqDto.CustomerReqDto;
import com.example.e_commerce.Dto.RespDto.CustomerRespDto;
import com.example.e_commerce.Exception.CustomerException;
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
    public CustomerRespDto addcustomer(CustomerReqDto customerReqDto) throws CustomerException
    {
        Customer customer = new Customer();
      try {
          customer.setName(customerReqDto.getName());
          customer.setEmail(customerReqDto.getEmail());
          customer.setAge(customerReqDto.getAge());
          customer.setMob(customerReqDto.getMob());
          customer.setAddress(customerReqDto.getAddress());
          customerRepository.save(customer);
      }
      catch (Exception e)
      {
          throw new CustomerException("email already registered");
      }
       CustomerRespDto customerRespDto=new CustomerRespDto();
       customerRespDto.setName(customer.getName());
        customerRespDto.setMessage("Customer Added");
        return customerRespDto;
    }
}
