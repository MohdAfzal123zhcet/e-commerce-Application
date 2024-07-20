package com.example.e_commerce.Dto.ReqDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerReqDto {

        String name;
        String email;
        Integer age;
        String mob;
        String address;
}
