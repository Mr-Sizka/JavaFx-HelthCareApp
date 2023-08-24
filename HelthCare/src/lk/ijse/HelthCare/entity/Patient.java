package lk.ijse.HelthCare.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class Patient {
    private String pId;
    private String name;
    private String address;
    private String contact;

}
