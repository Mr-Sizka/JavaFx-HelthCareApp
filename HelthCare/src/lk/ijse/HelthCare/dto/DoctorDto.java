package lk.ijse.HelthCare.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DoctorDto {
    private String dId;
    private String name;
    private String address;
    private String contact;
}
