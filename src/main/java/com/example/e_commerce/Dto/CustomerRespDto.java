package com.example.e_commerce.Dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRespDto {

    String name;
    String message;

}
