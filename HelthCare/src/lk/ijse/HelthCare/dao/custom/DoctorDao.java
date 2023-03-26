package lk.ijse.HelthCare.dao.custom;

import lk.ijse.HelthCare.dao.CrudDAO;
import lk.ijse.HelthCare.entity.Doctor;


import java.sql.SQLException;
import java.util.ArrayList;

public interface DoctorDao extends CrudDAO<Doctor,String> {
    public  ArrayList<Doctor> search(String text) throws SQLException, ClassNotFoundException;
}

