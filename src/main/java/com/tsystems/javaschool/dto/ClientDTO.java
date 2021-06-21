package com.tsystems.javaschool.dto;

import com.tsystems.javaschool.util.PasswordMatches;
import com.tsystems.javaschool.util.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatches
public class ClientDTO {
    @NotBlank(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotBlank(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String lastname;

    @ValidEmail
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @NotBlank(message = "Phone should not be empty")
    @Size(min = 5, max = 15, message = "Phone should be valid")
    private String phone;

    @Size(min = 5, max = 16, message = "Password should be between 5 and 16 characters")
    @NotEmpty(message = "Password should not be empty")
    private String password;
    private String matchingPassword;

    private String zip;
    private String country;
    private String city;
    private String street;
    private String building;
    private String apartment;
    private String userNameParent;



    //return true if address is empty
    public boolean addressIsEmpty() {
        return ((this.zip + this.country + this.city + this.street + this.building + this.apartment).length() == 0);
    }
}
