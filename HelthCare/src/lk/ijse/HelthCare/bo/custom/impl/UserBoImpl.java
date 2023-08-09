package lk.ijse.HelthCare.bo.custom.impl;

import lk.ijse.HelthCare.bo.custom.UserBo;
import lk.ijse.HelthCare.dao.DaoFactory;
import lk.ijse.HelthCare.dao.custom.UserDao;
import lk.ijse.HelthCare.dto.UserDto;
import lk.ijse.HelthCare.entity.User;

import java.sql.SQLException;

public class UserBoImpl implements UserBo {

    UserDao user = DaoFactory.getInstance().getDao(DaoFactory.DaoType.USER);

    @Override
    public boolean checkPassword(String email, String password) throws SQLException, ClassNotFoundException {
        return user.checkPassword(email, password);
    }

    @Override
    public boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException {
        return user.save(new User(dto.getEmail(), dto.getFullName(), dto.getContact(), dto.getPassword()));
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateUser(UserDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public UserDto getUser(String Id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
