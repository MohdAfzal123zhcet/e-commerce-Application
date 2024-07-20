package com.example.e_commerce.controller;

import com.example.e_commerce.Dto.ReqDto.SellerReqDto;
import com.example.e_commerce.Dto.RespDto.SellerRespDto;
import com.example.e_commerce.Service.ServiceInterfaceImplementation.SellerServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerServiceImplementation sellerServiceImplementation;
    @PostMapping("/add")
    public ResponseEntity addseller1(@RequestBody SellerReqDto sellerReqDto)
    {
        SellerRespDto sellerRespDto;
       try
       {
           sellerRespDto=sellerServiceImplementation.addSeller(sellerReqDto);
           return new ResponseEntity(sellerRespDto,HttpStatus.CREATED);
       }
       catch (Exception e)
       {
           return new  ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }
}
