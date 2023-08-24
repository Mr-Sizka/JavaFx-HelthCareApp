package lk.ijse.HelthCare.view.tm;


import javafx.scene.control.Button;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DoctorTm {
    private String id;
    private String name;
    private String address;
    private String contact;
    private Button btn;

}
