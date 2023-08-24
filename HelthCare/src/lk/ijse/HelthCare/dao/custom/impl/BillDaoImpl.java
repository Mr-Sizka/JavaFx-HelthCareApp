package lk.ijse.HelthCare.dao.custom.impl;

import lk.ijse.HelthCare.dao.CrudUtil;
import lk.ijse.HelthCare.dao.custom.BillDao;
import lk.ijse.HelthCare.dto.BillDto;
import lk.ijse.HelthCare.entity.Bill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BillDaoImpl implements BillDao {
    @Override
    public boolean save(Bill bill) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO bill VALUES(?,?,?,?,?)",bill.getBId(),bill.getDId(),bill.getPId(),bill.getAmount(),bill.getDate());
    }

    @Override
    public ArrayList<Bill> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM bill;");
        ArrayList<Bill> list = new ArrayList<>();
        while (rst.next()){
            list.add(new Bill(
                     rst.getString("billId"),
                     rst.getString("dId"),
                     rst.getString("pId"),
                    rst.getDouble("amount"),
                     rst.getDate("date")
            ));
        }
        return list;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM bill WHERE billId LIKE ?",id);
    }

    @Override
    public boolean update(Bill bill) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Bill get(String ID) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Bill> search(String text) throws SQLException, ClassNotFoundException {
        String searchText = "%"+text+"%";
       ArrayList<Bill> list =  new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM bill WHERE billId LIKE ? || amount LIKE ?", searchText, searchText);
        while (rst.next()){
            list.add(new Bill(
                    rst.getString("billId"),
                    rst.getString("dId"),
                    rst.getString("pId"),
                    rst.getDouble("amount"),
                    rst.getDate("date")
            ));
        }
        return list;
    }
}
