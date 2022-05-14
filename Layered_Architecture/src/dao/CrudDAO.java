package dao;

import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO {
    ArrayList<Object> getAll() throws SQLException, ClassNotFoundException;

    boolean save(Object dto) throws SQLException, ClassNotFoundException;

    boolean update(Object dto) throws SQLException, ClassNotFoundException;

    boolean exist(Object id) throws SQLException, ClassNotFoundException;

    boolean delete(Object id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    CustomerDTO search(String id) throws SQLException, ClassNotFoundException;
}
