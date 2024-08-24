package com.example.e_commerce.Dto.RespDto;

import com.example.e_commerce.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level= AccessLevel.PRIVATE)
    @Builder
    public class ProductResponseDto {

        String productName;

        String sellerName;

        int quantity;

        ProductStatus productStatus;
    }