package lk.ijse.HelthCare.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.HelthCare.bo.BoFactory;
import lk.ijse.HelthCare.bo.custom.UserBo;
import lk.ijse.HelthCare.dto.UserDto;

import java.io.IOException;

public class SignUpController {
    public AnchorPane SignupFormContext;
    public JFXTextField textEmail;
    public JFXTextField txtName;
    public JFXTextField txtContact;
    public JFXPasswordField txtPassword;
    public JFXPasswordField txtConfirm;

    public void initialize(){
        clear();
    }
    private final UserBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.USER);

    public void btnHaveOnAction(ActionEvent actionEvent) throws IOException {
        setUI("loginForm");
    }
    private void setUI(String loaction) throws IOException {
        Stage stage = (Stage)SignupFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+loaction+".fxml"))));
    }

    public void btnSignUpOnAction(ActionEvent actionEvent) throws IOException {
        if (
                textEmail.getText()==null||
                txtName.getText()==null||
                txtContact.getText()==null||
                txtPassword.getText()==null||
                txtConfirm.getText()==null)
        {
            new Alert(Alert.AlertType.WARNING,"Please Enter All Information").show();
        }else {
            if (txtPassword.getText().equals(txtConfirm.getText())){
                UserDto dto = new UserDto(textEmail.getText(),txtName.getText(),txtContact.getText(),txtPassword.getText());
                try {
                    boolean isSaved = bo.saveUser(dto);
                    if (isSaved){
                        new Alert(Alert.AlertType.CONFIRMATION,"User Saved!").show();
                        setUI("DashBoardForm");
                    }else {
                        new Alert(Alert.AlertType.ERROR,"Try Again").show();
                    }
                }catch (Exception e){
                    new Alert(Alert.AlertType.ERROR,"Try Again").show();
                }
            }else {
                new Alert(Alert.AlertType.ERROR,"Confirm Password Not Same").show();
            }
        }

    }

    private void clear(){
        textEmail.setText(null);
        txtName.setText(null);
        txtContact.setText(null);
        txtPassword.setText(null);
    }
}
