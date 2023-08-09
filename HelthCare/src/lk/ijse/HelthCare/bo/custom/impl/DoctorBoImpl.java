package lk.ijse.HelthCare.bo.custom.impl;

import lk.ijse.HelthCare.bo.custom.DoctorBo;
import lk.ijse.HelthCare.dao.DaoFactory;
import lk.ijse.HelthCare.dao.custom.DoctorDao;
import lk.ijse.HelthCare.dao.custom.PatientDao;
import lk.ijse.HelthCare.dto.DoctorDto;
import lk.ijse.HelthCare.entity.Doctor;

import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorBoImpl implements DoctorBo {

    DoctorDao dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.DOCTOR);
    @Override
    public boolean saveDoctor(DoctorDto dto) throws SQLException, ClassNotFoundException {
        return dao.save(new Doctor(dto.getdId(),dto.getName(),dto.getAddress(),dto.getContact()));
    }

    @Override
    public ArrayList<DoctorDto> getAllDoctors() throws SQLException, ClassNotFoundException {
        ArrayList<DoctorDto> dto= new ArrayList<>();
        for (Doctor d:dao.getAll()){
            dto.add(new DoctorDto(d.getdId(),d.getName(),d.getAddress(),d.getContact()));
        }
        return dto;
    }

    @Override
    public boolean deleteDoctor(String id) throws SQLException, ClassNotFoundException {
        return dao.delete(id);
    }

    @Override
    public boolean updateDoctor(DoctorDto dto) throws SQLException, ClassNotFoundException {
        return dao.update(new Doctor(dto.getdId(),dto.getName(), dto.getAddress(),dto.getContact()));
    }

    @Override
    public DoctorDto getDoctor(String Id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<DoctorDto> search(String text) throws SQLException, ClassNotFoundException {
        ArrayList<DoctorDto> dto=new ArrayList<>();
        for (Doctor d :dao.search(text)) {
            dto.add(new DoctorDto(d.getdId(),d.getName(),d.getAddress(),d.getContact()));
        }
        return dto;
    }
}
