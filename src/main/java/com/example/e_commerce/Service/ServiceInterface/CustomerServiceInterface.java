package com.example.e_commerce.Service.ServiceInterface;

import com.example.e_commerce.Dto.ReqDto.CustomerReqDto;
import com.example.e_commerce.Dto.RespDto.CustomerRespDto;
import com.example.e_commerce.Exception.CustomerException;

public interface CustomerServiceInterface {
    public CustomerRespDto addcustomer(CustomerReqDto customerReqDto)throws CustomerException;
}
