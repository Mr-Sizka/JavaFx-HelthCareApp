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
import lk.ijse.HelthCare.dao.custom.DoctorDao;
import lk.ijse.HelthCare.dao.custom.impl.DoctorDaoImpl;
import lk.ijse.HelthCare.dto.DoctorDto;
import lk.ijse.HelthCare.view.tm.DoctorTm;


import java.io.IOException;
import java.util.Optional;

public class DoctorFormContoller {
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

    private String sText="";

    private DoctorBo bo= BoFactory.getInstance().getBo(BoFactory.BoType.DOCTOR);

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
        ObservableList<DoctorTm> TmList= FXCollections.observableArrayList();
        try {
            for (DoctorDto dto : bo.search(text)) {
                Button btn = new Button("Delete");
                TmList.add(new DoctorTm(dto.getdId(),dto.getName(),dto.getAddress(),dto.getContact(),btn));

                btn.setOnAction(event -> {
                    Alert alert=new Alert(
                            Alert.AlertType.CONFIRMATION,
                            "Are You Sure?",
                            ButtonType.YES,
                            ButtonType.NO
                    );
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    try {
                        if (buttonType.get() == ButtonType.YES) {
                            bo.deleteDoctor(dto.getdId());
                            searchDoctors(text);
                        }
                    }catch (Exception e){
                        new Alert(Alert.AlertType.ERROR,"Try Againg").show();
                    }
                });
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        tblDoctors.setItems(TmList);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashBoardForm");
    }
    private void setUI(String location) throws IOException {
        Stage stage = (Stage) DoctorFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
    }

    public void NewDoctorOnAction(ActionEvent actionEvent) {

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if(txtId.getText()==null||txtName.getText()==null||txtAddress.getText()==null||txtContact.getText()==null) {
            new Alert(Alert.AlertType.ERROR, "Please Enter All Information").show();
        }else{
            DoctorDto dto = new DoctorDto(txtId.getText(), txtName.getText(), txtAddress.getText(), txtContact.getText());
            if (btnSave.getText().equals("Save Doctor")) {
                try {
                    boolean isSaved = bo.saveDoctor(dto);
                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Doctor Saved!").show();
                        clear();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again").show();
                    }
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Try Again").show();
                }
            } else {
                try {
                    boolean isSaved = bo.updateDoctor(dto);
                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "Doctor Updated!").show();
                        clear();
                        btnSave.setText("Save Doctor");
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again").show();
                    }
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, "Try Again").show();
                }

            }

        }
        searchDoctors(sText);
    }

    private void clear(){
        txtId.setText(null);
        txtName.setText(null);
        txtAddress.setText(null);
        txtContact.setText(null);
    }
}
