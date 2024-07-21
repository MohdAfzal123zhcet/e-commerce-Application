package com.example.e_commerce.Dto.ReqDto;

import com.example.e_commerce.Enum.CardType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardReqDto {

    String mob;
    String cardNo;

    int cvv;

    Date expiryDate;
    @Enumerated(EnumType.STRING)
    CardType cardType;
}
