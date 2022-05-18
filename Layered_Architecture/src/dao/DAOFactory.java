package dao;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static  DAOFactory getDaoFactory(){
        if(daoFactory==null){
            return new DAOFactory();
        }
        return daoFactory;
    }

    public static void test(){

    }


}
