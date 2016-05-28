package dao;

import dao.impl.DaoImpl;

/**
 * Created by user on 28.05.2016.
 */
public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){};

    public static DaoFactory getInstance() {
        if (instance == null)
        {
            instance = new DaoFactory();
        }
        return instance;
    }
    public IDao getDAO(String source){
        return new DaoImpl(source);
    }
}
