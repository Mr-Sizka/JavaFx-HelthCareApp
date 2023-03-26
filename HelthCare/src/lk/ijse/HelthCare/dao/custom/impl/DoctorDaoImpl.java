package lk.ijse.HelthCare.dao.custom.impl;

import lk.ijse.HelthCare.dao.CrudUtil;
import lk.ijse.HelthCare.entity.Doctor;
import lk.ijse.HelthCare.dao.custom.DoctorDao;
import lk.ijse.HelthCare.db.DBConnection;
import lk.ijse.HelthCare.dto.DoctorDto;
import lk.ijse.HelthCare.entity.Doctor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorDaoImpl implements DoctorDao {
    @Override
    public boolean save(Doctor d) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Doctor VALUES (?,?,?,?)",d.getdId(),d.getName(),d.getAddress(),d.getContact());
    }

    @Override
    public ArrayList<Doctor> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * From doctor; ");
        ArrayList<Doctor> doctors=new ArrayList<>();
        while(rst.next()){
            doctors.add(new Doctor(
                    rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getString(4)));
        }
        return doctors;

    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM doctor WHERE dId=?",id);
    }

    @Override
    public boolean update(Doctor d) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE doctor SET Name=?,Address=?,Contact=? WHERE dId=?",d.getName(),d.getAddress(),d.getContact(),d.getdId());
    }

    @Override
    public Doctor get(String ID) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Doctor> search(String text) throws SQLException, ClassNotFoundException {
        String tempText="%"+text+"%";
        ResultSet rst = CrudUtil.execute("SELECT * FROM doctor WHERE name LIKE ? || address LIKE ?", tempText, tempText);
        ArrayList<Doctor> doctors= new ArrayList<>();
        while (rst.next()){
            doctors.add(new Doctor(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return doctors;
    }
}
