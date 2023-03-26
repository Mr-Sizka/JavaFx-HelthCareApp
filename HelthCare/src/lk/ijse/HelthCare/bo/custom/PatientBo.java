package lk.ijse.HelthCare.bo.custom;

import lk.ijse.HelthCare.dto.PatientDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PatientBo {
    public boolean savePatient(PatientDto dto) throws SQLException, ClassNotFoundException;
    public ArrayList<PatientDto> getAllPatients() throws SQLException, ClassNotFoundException;
    public boolean deletePatient(String id) throws SQLException, ClassNotFoundException;
    public boolean updatePatient(PatientDto dto) throws SQLException, ClassNotFoundException;
    public PatientDto getPatient (String Id) throws SQLException,ClassNotFoundException;
    public ArrayList<PatientDto> search(String text) throws SQLException, ClassNotFoundException;

}
