package lk.ijse.HelthCare.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientDto {
    private String pId;
    private String name;
    private String address;
    private String contact;
}
