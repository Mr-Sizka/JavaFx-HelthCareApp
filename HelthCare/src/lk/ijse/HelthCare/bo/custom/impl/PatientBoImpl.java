package lk.ijse.HelthCare.bo.custom.impl;

import lk.ijse.HelthCare.bo.custom.PatientBo;
import lk.ijse.HelthCare.dao.DaoFactory;
import lk.ijse.HelthCare.dao.custom.DoctorDao;
import lk.ijse.HelthCare.dao.custom.PatientDao;
import lk.ijse.HelthCare.dto.PatientDto;
import lk.ijse.HelthCare.entity.Doctor;
import lk.ijse.HelthCare.entity.Patient;

import java.sql.SQLException;
import java.util.ArrayList;

public class PatientBoImpl implements PatientBo {

    PatientDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.PATIENT);
    @Override
    public boolean savePatient(PatientDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(new Patient(dto.getpId(),dto.getName(),dto.getAddress(),dto.getContact()));
    }

    @Override
    public ArrayList<PatientDto> getAllPatients() throws SQLException, ClassNotFoundException {
        ArrayList<PatientDto> dto= new ArrayList<>();
        for (Patient p: dao.getAll()) {
            dto.add(new PatientDto(p.getpId(), p.getName(), p.getAddress(), p.getContact()));
        }
        return dto;
    }

    @Override
    public boolean deletePatient(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public boolean updatePatient(PatientDto dto) throws SQLException, ClassNotFoundException {
        return dao.update(new Patient(dto.getpId(), dto.getName(), dto.getAddress(), dto.getContact()));
    }

    @Override
    public PatientDto getPatient(String Id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<PatientDto> search(String text) throws SQLException, ClassNotFoundException {
        ArrayList<PatientDto> patients=new ArrayList<>();
        for (Patient p:dao.search(text)) {
            patients.add(new PatientDto(p.getpId(),p.getName(),p.getAddress(),p.getContact()));
        }
        return patients;
    }
}
