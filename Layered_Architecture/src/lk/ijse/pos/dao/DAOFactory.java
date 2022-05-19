package lk.ijse.pos.dao;


import lk.ijse.pos.dao.custom.impl.*;

public class DAOFactory {
    //singleton design pattern
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }


    public static  DAOFactory getDaoFactory(){
        if(daoFactory==null){
            return new DAOFactory();
        }
        return daoFactory;
    }


    //factory design pattern
    public enum DAOTypes{
        CUSTOMER,ITEM,ORDER,ORDERDETAILS,QUERYDAO
    }

    //method for hide the object creation logic and return what client wants
    public SuperDAO getDAO(DAOTypes type){
        switch (type){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailsDAOImpl();
            case QUERYDAO:
                return new JoinQueryDAOImpl();
            default:
                return null;
        }
    }


}
