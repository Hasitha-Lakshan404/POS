package dao;

import db.DBConnection;
import javafx.scene.control.Alert;
import model.CustomerDTO;
import util.CrudUtil;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl  implements CustomerDAO  {
   /* @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }*/


    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDTO> cusList = new ArrayList<>();
        while (rst.next()) {
            cusList.add(new CustomerDTO(rst.getString(1), rst.getString(2), rst.getString(3)));
        }
        return cusList;
    }

    @Override
    public boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)", customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress());

    }

    @Override
    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?", customerDTO.getName(), customerDTO.getAddress(), customerDTO.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Customer WHERE id=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {

        ResultSet rst = CrudUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet result = CrudUtil.execute("SELECT id FROM Customer WHERE id=?", id);
        return result.next();
    }

    @Override
    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet=CrudUtil.execute("SELECT * FROM Customer WHERE id=?",id);
        if (resultSet.next()) {
            return new CustomerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        }
        return null;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomerByAddress(String address) {
        return null;
    }
}
