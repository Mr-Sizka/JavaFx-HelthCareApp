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
import lk.ijse.HelthCare.bo.custom.BillBo;
import lk.ijse.HelthCare.dto.BillDto;
import lk.ijse.HelthCare.view.tm.BillTm;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

public class PaymentFormContoller {
    public JFXTextField txtId;
    public JFXTextField txtPid;
    public JFXButton btnSave;
    public TableView tblBill;
    public TableColumn colIBillId;
    public TableColumn colPid;
    public TableColumn colDid;
    public TableColumn colAmount;
    public TableColumn colDate;
    public TableColumn colOption;
    public JFXTextField txtSearch;
    public JFXTextField txtBillId;
    public JFXTextField txtAmount;
    public JFXTextField txtDid;
    public AnchorPane paymentFormContext;

    private String sText = "";
    public void initialize(){
        clear();

        colIBillId.setCellValueFactory(new PropertyValueFactory<>("bId"));
        colDid.setCellValueFactory(new PropertyValueFactory<>("dId"));
        colPid.setCellValueFactory(new PropertyValueFactory<>("pId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchBills(sText);

        tblBill.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            if (newValue != null){
                setData((BillTm) newValue);
            }
        }));

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            sText=newValue;
            searchBills(sText);
        });
    }

    private void setData(BillTm tm){
        txtBillId.setText(tm.getBId());
        txtDid.setText(tm.getDId());
        txtPid.setText(tm.getPId());
        txtAmount.setText(String.valueOf(tm.getAmount()));
    }

    private void searchBills(String sText){
        ObservableList<BillTm> tmList = FXCollections.observableArrayList();
        try {
            for( BillDto dto : bo.search(sText)){
                Button btn = new Button("Delete");
                tmList.add(
                        new BillTm(
                                dto.getBId(),
                                dto.getDId(),
                                dto.getPId(),
                                dto.getAmount(),
                                dto.getDate(),
                                btn
                        )
                );
                btn.setOnAction(event -> {
                    Alert alert = new Alert(
                            Alert.AlertType.CONFIRMATION,
                            "Are You Sure?",
                            ButtonType.YES,
                            ButtonType.NO
                    );
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    try {
                        if (buttonType.isPresent()&&(buttonType.get()==ButtonType.YES)){
                            bo.deleteBill(dto.getBId());
                            searchBills(sText);
                        }
                    }catch (Exception e){
                        new Alert(Alert.AlertType.ERROR,"Try Again..!").show();
                    }
                });

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        tblBill.setItems(tmList);
    }

    private final BillBo bo = BoFactory.getInstance().getBo(BoFactory.BoType.BILL);
    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        setUI("DashboardForm");
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (Objects.equals(txtBillId.getText(), "") |
                Objects.equals(txtDid.getText(), "") |
                Objects.equals(txtPid.getText(), "") |
                Objects.equals(txtAmount.getText(), "")){
            new Alert(Alert.AlertType.WARNING,"Please Enter All Information...!").show();
        }else {
            BillDto newBill = new BillDto(
                    txtBillId.getText(),
                    txtDid.getText(),
                    txtPid.getText(),
                    Integer.parseInt(txtAmount.getText()),
                    Date.valueOf(LocalDate.now())
            );
            try {
                boolean isSave= bo.saveBill(newBill);
                if (isSave){
                    new Alert(Alert.AlertType.CONFIRMATION,"Bill Added...!").show();
                }
            }catch (Exception e){
                new Alert(Alert.AlertType.WARNING,"Please Try Again...!").show();
            }
        }
        clear();
        searchBills(sText);
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) paymentFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
    }

    public void clear(){
        txtBillId.setText("");
        txtDid.setText("");
        txtPid.setText("");
        txtAmount.setText("");
    }
}
