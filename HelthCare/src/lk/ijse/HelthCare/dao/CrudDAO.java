package lk.ijse.HelthCare.dao;


import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T,ID> {
    public boolean save(T t) throws SQLException, ClassNotFoundException;
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    public boolean delete(ID id) throws SQLException, ClassNotFoundException;
    public boolean update(T t) throws SQLException, ClassNotFoundException;
    public T get (ID ID) throws SQLException,ClassNotFoundException;
}
