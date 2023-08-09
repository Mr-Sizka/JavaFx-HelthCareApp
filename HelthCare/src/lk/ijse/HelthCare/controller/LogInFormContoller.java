package lk.ijse.HelthCare.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.org.omg.CORBA.Initializer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.HelthCare.bo.BoFactory;
import lk.ijse.HelthCare.bo.custom.UserBo;

import java.io.IOException;

public class LogInFormContoller {
    public AnchorPane loginFormContext;
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;

    public void initialize(){
        clear();
    }
    UserBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.USER);
    public void bntLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (txtEmail.getText()==null||txtPassword.getText()==null){
            new Alert(Alert.AlertType.WARNING,"Please Enter All Information").show();
        }else {
            try {
                boolean valid = bo.checkPassword(txtEmail.getText(), txtPassword.getText());
                if (valid){
                    setUI("DashBoardForm");
                }else {
                    new Alert(Alert.AlertType.ERROR,"Incorrect User").show();
                }
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR,"Try Again").show();
            }
        }


    }

    public void btnCreateOnAction(ActionEvent actionEvent) throws IOException {
        setUI("SignUp");
    }

    private void setUI(String loaction) throws IOException {
        Stage stage = (Stage)loginFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+loaction+".fxml"))));
    }

    private void clear(){
        txtEmail.setText(null);
        txtPassword.setText(null);
    }
}
