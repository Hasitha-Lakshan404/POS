package dao.custom;

import dao.CrudDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer, String> {

    ArrayList<CustomerDTO> getAllCustomerByAddress(String address);

}
