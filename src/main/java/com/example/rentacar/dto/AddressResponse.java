package com.example.rentacar.dto;

import com.example.rentacar.entity.User;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressResponse {

    private String city;
    private String country;
    private String streetName;
    private User user;
}
