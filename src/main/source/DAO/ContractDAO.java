package DAO;

import org.hibernate.Session;
import store.Contract;
import util.Hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class ContractDAO {
    public void addContract(Contract contract) throws SQLException {
        Session session = null;
        session = Hibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(contract);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void updateContract(int contract_id, Contract contract) throws SQLException {
        Session session = null;
        session = Hibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(contract);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public Contract getContractById(int contract_id) throws SQLException {
        Session session = null;
        Contract contract = null;
        session = Hibernate.getSessionFactory().openSession();
        contract = session.load(Contract.class, contract_id);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return contract;
    }

    public Collection getAllContracts() throws SQLException {
        Session session = null;
        List contracts;
        session = Hibernate.getSessionFactory().openSession();
        contracts = session.createQuery("from Contract").list();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return contracts;
    }

    public void deleteContract(Contract contract) throws SQLException {
        Session session = null;
        session = Hibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(contract);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
