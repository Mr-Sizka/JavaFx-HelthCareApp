package lk.ijse.HelthCare.dao.custom;

import lk.ijse.HelthCare.dao.CrudDAO;
import lk.ijse.HelthCare.entity.Bill;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BillDao extends CrudDAO <Bill,String> {

    public ArrayList<Bill> search(String text) throws SQLException, ClassNotFoundException;
}
