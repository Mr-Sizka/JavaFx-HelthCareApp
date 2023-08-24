package lk.ijse.HelthCare.bo.custom;

import lk.ijse.HelthCare.dto.BillDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BillBo {

    public boolean saveBill(BillDto dto) throws SQLException, ClassNotFoundException;
    public ArrayList<BillDto> search(String text) throws SQLException, ClassNotFoundException;
    public ArrayList<BillDto> getAllBills() throws SQLException, ClassNotFoundException;

    public boolean deleteBill(String billId) throws SQLException, ClassNotFoundException;
}
