package kz.wonder.wonderuser.dto.request;

import lombok.Data;

@Data
public class ClientRequestDto {

    private String firstname;

    private String lastname;

    private String phoneNumber;

    private String email;

    private String password;
}
