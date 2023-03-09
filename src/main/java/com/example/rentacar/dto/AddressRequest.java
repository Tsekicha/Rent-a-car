package com.example.rentacar.dto;

import com.example.rentacar.entity.User;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressRequest {

    private String city;
    private String cityZipCode;
    private String country;
    private String streetName;
    private String streetNumber;
    private User user;
}
