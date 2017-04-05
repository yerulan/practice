package util;

import DAO.*;
import store.Contact;
import store.Contract;
import store.Servant;
import store.Service;

public class Factory {

    private static ClientDAO clientDAO = null;
    private static ServantDAO servantDAO = null;
    private static ContactDAO contactDAO = null;
    private static ServiceDAO serviceDAO = null;
    private static ContractDAO contractDAO = null;
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

    public ServantDAO getServantDAO() {
        if (servantDAO == null) {
            servantDAO = new ServantDAO();
        }
        return servantDAO;
    }

    public ServiceDAO getServiceDAO() {
        if (serviceDAO == null) {
            serviceDAO = new ServiceDAO();
        }
        return serviceDAO;
    }

    public ContactDAO getContactDAO() {
        if (contactDAO == null) {
            contactDAO = new ContactDAO();
        }
        return contactDAO;
    }

    public ContractDAO getContractDAO() {
        if (contractDAO == null) {
            contractDAO = new ContractDAO();
        }
        return contractDAO;
    }
}