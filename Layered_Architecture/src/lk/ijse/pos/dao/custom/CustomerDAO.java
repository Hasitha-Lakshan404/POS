package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer, String> {

    ArrayList<CustomerDTO> getAllCustomerByAddress(String address);

}
