package lk.ijse.HelthCare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class BillDto {
    private String bId;
    private String dId;
    private String pId;
    private double amount;
    private Date date;
}
