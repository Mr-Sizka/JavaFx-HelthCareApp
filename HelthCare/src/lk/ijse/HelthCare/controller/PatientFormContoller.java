package lk.ijse.HelthCare.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.HelthCare.bo.BoFactory;
import lk.ijse.HelthCare.bo.custom.DoctorBo;
import lk.ijse.HelthCare.bo.custom.PatientBo;
import lk.ijse.HelthCare.dto.PatientDto;
import lk.ijse.HelthCare.view.tm.PatientTm;

import java.io.IOException;
import java.util.Optional;

import static java.awt.SystemColor.text;

public class PatientFormContoller {
    public AnchorPane PatientsFormContext;
    public JFXTextField txtId;
    public JFXTextField txtContact;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public TableView tblPatient;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colOption;
    public JFXTextField txtSearch;
    public JFXButton btnSave;

    private PatientBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.PATIENT);
    private String sText="";

    public void initialize(){
        clear();
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));



        searchPatient(sText);

        tblPatient.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                setData((PatientTm)newValue);
            }
        });

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            sText=newValue;
            searchPatient(sText);
        });


    }



    private void clear() {
        txtId.setText(null);
        txtName.setText(null);
        txtAddress.setText(null);
        txtContact.setText(null);
    }

    private void setData(PatientTm tm) {
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtContact.setText(tm.getContact());
        btnSave.setText("Update Patient");
    }

    private void searchPatient(String Text) {
        ObservableList<PatientTm> TmList = FXCollections.observableArrayList();
        try {
            for (PatientDto dto : bo.search(Text)) {
                Button btn = new Button("Delete");
                TmList.add(new PatientTm(dto.getpId(), dto.getName(), dto.getAddress(), dto.getContact(), btn));

                btn.setOnAction(event -> {
                    Alert alert = new Alert(
                            Alert.AlertType.CONFIRMATION,
                            "Are You Sure?",
                            ButtonType.YES,
                            ButtonType.NO
                    );
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    try {
                        if (buttonType.get() == ButtonType.YES) {
                            bo.deletePatient(dto.getpId());
                            searchPatient(Text);
                        }
                    } catch (Exception e) {
                        new Alert(Alert.AlertType.ERROR, "Try Againg").show();
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        tblPatient.setItems(TmList);

    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashboardForm");
    }

    public void btnNewOnAction(ActionEvent actionEvent) {

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if(txtId.getText()==null||txtName.getText()==null||txtAddress.getText()==null||txtContact.getText()==null) {
            new Alert(Alert.AlertType.ERROR, "Please Enter All Information").show();
        }else {
            PatientDto dto = new PatientDto(txtId.getText(), txtName.getText(), txtAddress.getText(), txtContact.getText());
            if(btnSave.getText().equals("Save Patient")){
                try {
                    boolean isSaved = bo.savePatient(dto);
                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Patient Saved...!").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Try Againg").show();
                    }
                }catch (Exception e){
                    new Alert(Alert.AlertType.ERROR,"Try Againg").show();
                }
            }else{
                try {
                    boolean isUpdated = bo.updatePatient(dto);
                    if (isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Doctor Updated...!").show();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Try Againg").show();
                    }
                }catch (Exception e){
                    new Alert(Alert.AlertType.ERROR,"Try Againg").show();
                }
            }
        }
        searchPatient(sText);
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) PatientsFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
    }
}
