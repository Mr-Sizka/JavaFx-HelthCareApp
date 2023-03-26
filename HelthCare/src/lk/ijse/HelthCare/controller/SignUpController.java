package lk.ijse.HelthCare.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {
    public AnchorPane SignupFormContext;



    public void btnHaveOnAction(ActionEvent actionEvent) throws IOException {
        setUI("loginForm");
    }
    private void setUI(String loaction) throws IOException {
        Stage stage = (Stage)SignupFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+loaction+".fxml"))));
    }

    public void btnSignUpOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashBoardForm");
    }
}
