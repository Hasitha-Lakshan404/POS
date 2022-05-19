package lk.ijse.pos.dao;

public class Test {
    public static void main(String[] args) {
        DAOFactory daoFactory = DAOFactory.getDaoFactory();
        daoFactory.getDAO(DAOFactory.DAOTypes.ITEM);

    }
}
