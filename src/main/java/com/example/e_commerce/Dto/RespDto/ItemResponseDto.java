
package com.example.e_commerce.Dto.RespDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder
public class ItemResponseDto {

    String productName;

    int priceOfOneItem;

    int totalPrice;

    int quantity;
}
