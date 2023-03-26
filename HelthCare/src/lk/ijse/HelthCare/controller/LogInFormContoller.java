package lk.ijse.HelthCare.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInFormContoller {
    public AnchorPane loginFormContext;

    public void bntLoginOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashBoardForm");
    }

    public void btnCreateOnAction(ActionEvent actionEvent) throws IOException {
        setUI("SignUp");
    }

    private void setUI(String loaction) throws IOException {
        Stage stage = (Stage)loginFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+loaction+".fxml"))));
    }
}
