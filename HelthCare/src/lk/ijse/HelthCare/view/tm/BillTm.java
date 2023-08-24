package lk.ijse.HelthCare.view.tm;

import javafx.scene.control.Button;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BillTm {
    private String bId;
    private String dId;
    private String pId;
    private double amount;
    private Date date;
    private Button btn;
}
