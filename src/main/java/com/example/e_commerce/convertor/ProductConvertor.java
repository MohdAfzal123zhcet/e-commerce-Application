package com.example.e_commerce.convertor;

import com.example.e_commerce.Enum.ProductStatus;
import com.example.e_commerce.Dto.ReqDto.ProductRequestDto;
import com.example.e_commerce.Dto.RespDto.ProductResponseDto;
import com.example.e_commerce.model.Product;
    public class ProductConvertor {

        public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){

            return Product.builder()
                    .name(productRequestDto.getProductName())
                    .price(productRequestDto.getPrice())
                    .productCategory(productRequestDto.getProductCategory())
                    .quantity(productRequestDto.getQuantity())
                    .productStatus(ProductStatus.AVAILABLE)
                    .build();
        }

        public static ProductResponseDto ProductToProductResponseDto(Product product){
            return ProductResponseDto.builder()
                    .productName(product.getName())
                    .sellerName(product.getSeller().getName())
                    .quantity(product.getQuantity())
                    .productStatus(product.getProductStatus())
                    .build();
        }
    }



