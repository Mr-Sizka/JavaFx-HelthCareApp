package lk.ijse.HelthCare.dao;

import lk.ijse.HelthCare.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface CrudUtil {
    public static <T> T execute(String sql, Object...prams) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        for (int i = 0; i < prams.length; i++) {
            stm.setObject((i+1),prams[i]);
        }
        if(sql.startsWith("SELECT")){
            return (T)stm.executeQuery();
        }
        return (T)(Boolean)(stm.executeUpdate()>0);
    }
}
