package lk.ijse.HelthCare.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class DashBoardFormController {
    public AnchorPane DashBoardContext;

    public void openDoctorOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DoctorForm");
    }

    public void openPatientsOnAction(ActionEvent actionEvent) throws IOException {
        setUI("PatientForm");
    }

    public void openPaymentsOnAction(ActionEvent actionEvent) throws IOException {
        setUI("PaymentForm");
    }

    public void openDocumentsOnAction(ActionEvent actionEvent) {
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) DashBoardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
    }
}
