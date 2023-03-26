package lk.ijse.HelthCare.dao;

import lk.ijse.HelthCare.dao.custom.impl.DoctorDaoImpl;
import lk.ijse.HelthCare.dao.custom.impl.PatientDaoImpl;

public class DaoFactory {
    private static DaoFactory daofactory;

    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return daofactory==null? (daofactory=new DaoFactory()):daofactory;
    }

    public enum DaoType{
        PATIENT,DOCTOR
    }

    public <T> T getDao(DaoType type){
        switch (type){
            case DOCTOR: return (T) new DoctorDaoImpl();
            case PATIENT: return (T) new PatientDaoImpl();
            default:return null;
        }
    }
}
