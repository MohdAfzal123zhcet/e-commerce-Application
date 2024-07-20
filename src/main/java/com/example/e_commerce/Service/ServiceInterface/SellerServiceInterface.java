package com.example.e_commerce.Service.ServiceInterface;

import com.example.e_commerce.Dto.ReqDto.SellerReqDto;
import com.example.e_commerce.Dto.RespDto.SellerRespDto;
import com.example.e_commerce.Exception.SellerException;

public interface SellerServiceInterface {
    public SellerRespDto addSeller(SellerReqDto sellerReqDto) throws SellerException;
}
