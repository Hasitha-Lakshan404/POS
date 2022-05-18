package dao;

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

    public void getDAO(DAOTypes type){
        switch (type){
            case CUSTOMER:
                return;
            case ITEM:
                return;
            case ORDER:
                return;
            case ORDERDETAILS:
                return;
            case QUERYDAO:
                return;
        }
    }


}
