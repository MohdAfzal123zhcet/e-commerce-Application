package com.example.e_commerce.Dto.RespDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardRespDto {
    String cardNo;
    String name;
}
