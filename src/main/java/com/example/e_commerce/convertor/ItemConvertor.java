package com.example.e_commerce.convertor;

import com.example.e_commerce.Dto.ReqDto.ItemRequestDto;
import com.example.e_commerce.Dto.RespDto.ItemResponseDto;
import com.example.e_commerce.model.Item;

    public class ItemConvertor {

        public static Item ItemRequestDtoToItem(ItemRequestDto itemRequestDto) {
            return Item.builder()
                    .requiredQuantity(itemRequestDto.getRequiredQuantity())
                    .build();
        }

        public static ItemResponseDto ItemToItemResponseDto(Item item) {

            return ItemResponseDto.builder()
                    .priceOfOneItem(item.getProduct().getPrice())
                    .productName(item.getProduct().getName())
                    .quantity(item.getRequiredQuantity())
                    .totalPrice(item.getRequiredQuantity() * item.getProduct().getPrice())
                    .build();
        }
    }
