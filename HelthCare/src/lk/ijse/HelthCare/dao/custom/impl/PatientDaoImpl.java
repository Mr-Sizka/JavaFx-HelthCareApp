package lk.ijse.HelthCare.dao.custom.impl;

import lk.ijse.HelthCare.dao.CrudUtil;
import lk.ijse.HelthCare.dao.custom.PatientDao;
import lk.ijse.HelthCare.db.DBConnection;
import lk.ijse.HelthCare.dto.PatientDto;
import lk.ijse.HelthCare.entity.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PatientDaoImpl implements PatientDao {
    @Override
    public boolean save(Patient p) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO patient VALUES(?,?,?,?)",p.getpId(),p.getName(),p.getAddress(),p.getContact());
    }

    @Override
    public ArrayList<Patient> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Patient> patients = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM patient;");
        while (rst.next()){
            patients.add(new Patient(
                    rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getString(4)));
        }
        return patients;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM patient WHERE pId=?",id);
    }

    @Override
    public boolean update(Patient dto) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE patient SET Name=?,Address=?,Contact=? WHERE pId=?",dto.getName(),dto.getAddress(),dto.getContact(),dto.getpId());
    }

    @Override
    public Patient get(String ID) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Patient> search(String text) throws SQLException, ClassNotFoundException {
        String tempText = "%"+text+"%";
        ArrayList<Patient> patients = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM patient WHERE name LIKE ? || address LIKE ?", tempText, tempText);
        while (rst.next()){
            patients.add(new Patient(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4) ));
        }
        return patients;
    }
}
