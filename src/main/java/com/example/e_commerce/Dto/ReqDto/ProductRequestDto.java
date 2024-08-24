package com.example.e_commerce.Dto.ReqDto;

import com.example.e_commerce.Enum.ProductCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level= AccessLevel.PRIVATE)
    @Builder
    public class ProductRequestDto {

        int sellerId;

        String productName;

        int price;

        int quantity;

        ProductCategory productCategory;
    }