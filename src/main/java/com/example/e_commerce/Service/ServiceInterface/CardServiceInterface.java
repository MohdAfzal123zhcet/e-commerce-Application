package com.example.e_commerce.Service.ServiceInterface;

import com.example.e_commerce.Dto.ReqDto.CardReqDto;
import com.example.e_commerce.Dto.RespDto.CardRespDto;
import com.example.e_commerce.Exception.CustomerException;

public interface CardServiceInterface {
    public CardRespDto addCard(CardReqDto cardReqDto)throws CustomerException;
}
