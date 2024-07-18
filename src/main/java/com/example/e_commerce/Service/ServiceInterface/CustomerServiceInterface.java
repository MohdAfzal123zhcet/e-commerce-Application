package com.example.e_commerce.Service.ServiceInterface;

import com.example.e_commerce.Dto.CustomerReqDto;
import com.example.e_commerce.Dto.CustomerRespDto;

public interface CustomerServiceInterface {
    public CustomerRespDto addcustomer(CustomerReqDto customerReqDto);
}
