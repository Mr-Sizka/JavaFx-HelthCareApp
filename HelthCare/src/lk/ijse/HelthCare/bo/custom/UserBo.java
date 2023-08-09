package lk.ijse.HelthCare.bo.custom;


import lk.ijse.HelthCare.dto.UserDto;

import java.sql.SQLException;

public interface UserBo {
    public boolean checkPassword(String email, String password) throws SQLException, ClassNotFoundException;
    public boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException;
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException;
    public boolean updateUser(UserDto dto) throws SQLException, ClassNotFoundException;
    public UserDto getUser (String Id) throws SQLException,ClassNotFoundException;
}
