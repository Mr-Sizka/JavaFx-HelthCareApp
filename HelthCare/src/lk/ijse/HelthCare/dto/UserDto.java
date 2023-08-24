package lk.ijse.HelthCare.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private String email;
    private String fullName;
    private String contact;
    private String password;
}
