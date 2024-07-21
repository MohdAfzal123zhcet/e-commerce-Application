package com.example.e_commerce.convertor;

import com.example.e_commerce.Dto.ReqDto.CardReqDto;
import com.example.e_commerce.Dto.RespDto.CardRespDto;
import com.example.e_commerce.model.Card;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardConvertor {
    public Card CardReqDtoToCard(CardReqDto cardReqDto)
    {
        return Card.builder()
                .cardNo(cardReqDto.getCardNo())
                .cvv(cardReqDto.getCvv())
                .expiryDate(cardReqDto.getExpiryDate())
                .cardType(cardReqDto.getCardType())
                .build();
    }


}
