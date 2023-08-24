package lk.ijse.HelthCare.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
@Setter
@Getter
@AllArgsConstructor
@ToString
public class Bill {
    private String bId;
    private String dId;
    private String pId;
    private double amount;
    private Date date;
}
