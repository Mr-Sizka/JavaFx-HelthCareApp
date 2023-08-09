package lk.ijse.HelthCare.dao.custom.impl;

import lk.ijse.HelthCare.dao.CrudUtil;
import lk.ijse.HelthCare.dao.custom.UserDao;
import lk.ijse.HelthCare.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    @Override
    public Boolean checkPassword(String email, String password) throws SQLException, ClassNotFoundException {
        ResultSet executed = CrudUtil.execute("SELECT * FROM user WHERE email LIKE ?", email);
        if (executed.next()){
            return password.equals(executed.getString(4));
        }
        return false;
    }

    @Override
    public boolean save(User user) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO User VALUES (?,?,?,?)",user.getEmail(),user.getFullName(),user.getContact(),user.getPassword());
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(User user) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User get(String ID) throws SQLException, ClassNotFoundException {
        return null;
    }
}
