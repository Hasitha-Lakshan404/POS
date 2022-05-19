package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.JoinQueryDAO;
import lk.ijse.pos.dto.CustomDTO;
import lk.ijse.pos.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class JoinQueryDAOImpl implements JoinQueryDAO {

    @Override
    public ArrayList<CustomDTO> searchOrderByOrderId(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT Orders.Oid,Orders.date,Orders.CustomerID,OrderDetails.itemCode,OrderDetails.qty,OrderDetails.unitPrice from Orders inner join OrderDetails ON Orders.oid = OrderDetails.oid=?";
        ResultSet rst = CrudUtil.execute(sql, id);
        ArrayList<CustomDTO> orderRecords = new ArrayList();

        /*while (rst.next()) {
            String oid = rst.getString(1);
            String date = rst.getString(2);
            String customerID = rst.getString(3);
            String itemCode = rst.getString(4);
            int qty = rst.getInt(5);
            BigDecimal unitPrice = rst.getBigDecimal(6);

        }*/

        while (rst.next()) {
            orderRecords.add(new CustomDTO(
                    rst.getString(1),
                    LocalDate.parse(rst.getString(2)),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getBigDecimal(6)));
        }
        return orderRecords;
    }
}
