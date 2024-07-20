package com.example.e_commerce.convertor;

import com.example.e_commerce.Dto.ReqDto.SellerReqDto;
import com.example.e_commerce.Dto.RespDto.SellerRespDto;
import com.example.e_commerce.model.Seller;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerConvertor {
    public static  Seller SellerReqDtoToSeller(SellerReqDto sellerReqDto) {
        return Seller.builder().name(sellerReqDto.getName())
                .age(sellerReqDto.getAge())
                .mob(sellerReqDto.getMob())
                .email(sellerReqDto.getEmail())
                .build();

    }

    public static  SellerRespDto SellerToSellerRespDto(Seller seller)
    {
              return SellerRespDto.builder()
                .name(seller.getName())
                      .message("seller has been added")
                .build();
    }
}
