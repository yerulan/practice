package util;

import DAO.ClientDAO;

public class Factory {

    private static ClientDAO clientDAO = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public ClientDAO getClientDAO(){
        if (clientDAO == null){
            clientDAO = new ClientDAO();
        }
        return clientDAO;
    }
}