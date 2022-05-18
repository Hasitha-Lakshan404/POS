package bo.custom.impl;

import bo.custom.PurchaseOrderBO;
import dao.DAOFactory;
import dao.custom.*;
import db.DBConnection;
import model.CustomerDTO;
import model.ItemDTO;
import model.OrderDTO;
import model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {


    //Hiding the Object Creation logic Using the Factory Pattern
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final OrderDetailsDAO orderDetailDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    private final JoinQueryDAO joinQueryDAO = (JoinQueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);

    @Override
    public boolean purchaseOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        /*Transaction*/


        Connection connection = DBConnection.getDbConnection().getConnection();

        if (orderDAO.exist(orderId)) {

        }

        connection.setAutoCommit(false);


        boolean save = orderDAO.save(new OrderDTO(orderId, orderDate, customerId));

        if (!save) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }



        for (OrderDetailDTO detail : orderDetails) {

            boolean save1 = orderDetailDAO.save(detail);

            if (!save1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }


            ItemDTO item = searchItem(detail.getItemCode());
            item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

            boolean update = itemDAO.update(item);
            if (!update) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
//        return false;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.search(id);
    }

    @Override
    public ItemDTO searchItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.search(id);
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public boolean checkCustomerIsAvailable(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID();
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        return customerDAO.getAll();
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }

}
