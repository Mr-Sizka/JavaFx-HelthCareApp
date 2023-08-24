package lk.ijse.HelthCare.common;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Paint;



public class TxtValidator {
    public Boolean validateFields(JFXTextField...params){
        boolean valid = true;
        for (JFXTextField item : params) {
            if (item.getText()==null){
                item.setUnFocusColor(Paint.valueOf("#FF0000"));
                valid = false;
            }
        }
        return valid;
    }
}
