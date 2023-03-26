package lk.ijse.HelthCare.dao.custom;

import lk.ijse.HelthCare.dao.CrudDAO;
import lk.ijse.HelthCare.dto.PatientDto;
import lk.ijse.HelthCare.entity.Patient;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PatientDao extends CrudDAO<Patient,String> {
    public ArrayList<Patient> search(String text) throws SQLException, ClassNotFoundException;
}
