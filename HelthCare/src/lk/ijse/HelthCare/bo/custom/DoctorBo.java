package lk.ijse.HelthCare.bo.custom;

import lk.ijse.HelthCare.dto.DoctorDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DoctorBo {
    public boolean saveDoctor(DoctorDto dto) throws SQLException, ClassNotFoundException;
    public ArrayList<DoctorDto> getAllDoctors() throws SQLException, ClassNotFoundException;
    public boolean deleteDoctor(String id) throws SQLException, ClassNotFoundException;
    public boolean updateDoctor(DoctorDto dto) throws SQLException, ClassNotFoundException;
    public DoctorDto getDoctor (String Id) throws SQLException,ClassNotFoundException;
    public ArrayList<DoctorDto> search(String text) throws SQLException, ClassNotFoundException;


}
