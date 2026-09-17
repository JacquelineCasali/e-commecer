package com.app.ecom.dto;

import lombok.Data;
/* Data cria os gest e ser*/
@Data
public class AddressDTO {
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}
