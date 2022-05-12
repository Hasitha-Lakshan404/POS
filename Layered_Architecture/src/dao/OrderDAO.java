package dao;

import java.sql.SQLException;

public interface OrderDAO {
    String generateNewID() throws SQLException, ClassNotFoundException;
}
