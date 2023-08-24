package lk.ijse.HelthCare.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.HelthCare.bo.BoFactory;
import lk.ijse.HelthCare.bo.custom.DoctorBo;
import lk.ijse.HelthCare.common.TxtValidator;
import lk.ijse.HelthCare.dto.DoctorDto;
import lk.ijse.HelthCare.view.tm.DoctorTm;


import java.io.IOException;
import java.util.Optional;

public class DoctorFormController {
    public AnchorPane DoctorFormContext;
    public JFXTextField txtId=null;
    public JFXTextField txtContact=null;
    public JFXTextField txtAddress=null;
    public JFXTextField txtName=null;
    public TableView tblDoctors;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colOption;
    public JFXTextField txtSearch;
    public JFXButton btnSave;

    private final String tryAgainText = "Try Again ...!";

    private String sText="";

    private final DoctorBo bo= BoFactory.getInstance().getBo(BoFactory.BoType.DOCTOR);

    public void initialize(){
        clear();

        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchDoctors(sText);
       tblDoctors.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null) {setData((DoctorTm) newValue);}
        });

       txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
           sText=newValue;
           searchDoctors(sText);
       });
    }

    private void setData(DoctorTm tm) {
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtContact.setText(tm.getContact());
        btnSave.setText("Update Doctor");
    }



    private void searchDoctors(String text) {
        ObservableList<DoctorTm> tmList= FXCollections.observableArrayList();
        try {
            for (DoctorDto dto : bo.search(text)) {
                Button btn = new Button("Delete");
                tmList.add(new DoctorTm(dto.getDId(),dto.getName(),dto.getAddress(),dto.getContact(),btn));

                btn.setOnAction(event -> {
                    Alert alert=new Alert(
                            Alert.AlertType.CONFIRMATION,
                            "Are You Sure?",
                            ButtonType.YES,
                            ButtonType.NO
                    );
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    try {
                        if (buttonType.isPresent() && (buttonType.get() == ButtonType.YES)) {
                                bo.deleteDoctor(dto.getDId());
                                searchDoctors(text);

                        }
                    }catch (Exception e){
                        new Alert(Alert.AlertType.ERROR,tryAgainText).show();
                    }
                });
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        tblDoctors.setItems(tmList);
    }

    public void btnBackOnAction() throws IOException {
        setUI("DashBoardForm");
    }
    private void setUI(String location) throws IOException {
        Stage stage = (Stage) DoctorFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
    }


    public void btnSaveOnAction() {
        if (new TxtValidator().validateFields(txtId,txtName,txtAddress,txtContact)) {
            DoctorDto dto = new DoctorDto(txtId.getText(), txtName.getText(), txtAddress.getText(), txtContact.getText());
            if (btnSave.getText().equals("Save Doctor")) {
                saveDoctor(dto);
            } else {
                updateDoctor(dto);
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please Enter All Information").show();
        }
        searchDoctors(sText);
    }

    private void saveDoctor(DoctorDto dto){
        try {
            boolean isSaved = bo.saveDoctor(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Doctor Saved!").show();
                clear();
            } else {
                new Alert(Alert.AlertType.WARNING, tryAgainText).show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, tryAgainText).show();
        }
    }

    private void updateDoctor(DoctorDto dto){
        try {
            boolean isSaved = bo.updateDoctor(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Doctor Updated!").show();
                clear();
                btnSave.setText("Save Doctor");
            } else {
                new Alert(Alert.AlertType.WARNING, tryAgainText).show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, tryAgainText).show();
        }
    }
    private void clear(){
        txtId.setText(null);
        txtName.setText(null);
        txtAddress.setText(null);
        txtContact.setText(null);
    }
}
