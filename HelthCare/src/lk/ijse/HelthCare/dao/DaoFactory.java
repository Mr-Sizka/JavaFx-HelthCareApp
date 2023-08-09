package lk.ijse.HelthCare.dao;

import lk.ijse.HelthCare.dao.custom.impl.DoctorDaoImpl;
import lk.ijse.HelthCare.dao.custom.impl.PatientDaoImpl;
import lk.ijse.HelthCare.dao.custom.impl.UserDaoImpl;

public class DaoFactory {
    private static DaoFactory daofactory;

    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return daofactory==null? (daofactory=new DaoFactory()):daofactory;
    }

    public enum DaoType{
        PATIENT, USER, DOCTOR
    }

    public <T> T getDao(DaoType type){
        switch (type){
            case DOCTOR: return (T) new DoctorDaoImpl();
            case PATIENT: return (T) new PatientDaoImpl();
            case USER: return (T) new UserDaoImpl();
            default:return null;
        }
    }
}
