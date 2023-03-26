package lk.ijse.HelthCare.bo;

import lk.ijse.HelthCare.bo.custom.impl.DoctorBoImpl;
import lk.ijse.HelthCare.bo.custom.impl.PatientBoImpl;
import lk.ijse.HelthCare.dao.custom.impl.DoctorDaoImpl;
import lk.ijse.HelthCare.dao.custom.impl.PatientDaoImpl;

public class BoFactory {
    private static BoFactory bofactory;

    private BoFactory(){}

    public static BoFactory getInstance(){
        return bofactory==null? (bofactory=new BoFactory()):bofactory;
    }

    public enum BoType{
        PATIENT,DOCTOR
    }

    public <T> T getBo(BoType type){
        switch (type){
            case DOCTOR: return (T) new DoctorBoImpl();
            case PATIENT: return (T) new PatientBoImpl();
            default:return null;
        }
    }
}
