package lk.ijse.HelthCare.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class User {

    private String email;
    private String fullName;
    private String contact;
    private String password;

}
