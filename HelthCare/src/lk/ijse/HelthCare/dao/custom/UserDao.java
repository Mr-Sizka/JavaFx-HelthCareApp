package lk.ijse.HelthCare.dao.custom;

import lk.ijse.HelthCare.dao.CrudDAO;
import lk.ijse.HelthCare.entity.User;

import java.sql.SQLException;

public interface UserDao extends CrudDAO<User,String> {

    Boolean checkPassword(String userName , String password) throws SQLException, ClassNotFoundException;
}
