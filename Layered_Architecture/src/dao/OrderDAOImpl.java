package dao;

import model.OrderDTO;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAOImpl implements  OrderDAO{

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }


}
