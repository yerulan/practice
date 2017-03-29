package DAO;

import org.hibernate.Session;
import store.Client;
import store.Servant;
import util.Hibernate;

import javax.swing.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClientDAO{

    public void addClient(Client client) throws SQLException {
        Session session = null;
        try {
            session = Hibernate.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Insert error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {

                session.close();
            }
        }
    }

    public void updateClient(int client_id, Client client) throws SQLException {
        Session session = null;
        try {
            session = Hibernate.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Insert error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Client getClientById(Long client_id) throws SQLException {
        Session session = null;
        Client client = null;
        try {
            session = Hibernate.getSessionFactory().openSession();
            client = session.load(Client.class, client_id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"FindById error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return client;
    }

    public Collection getAllClients() throws SQLException {
        Session session = null;
        List clients = new ArrayList<Client>();
        try {
            session = Hibernate.getSessionFactory().openSession();
            clients = session.createQuery("from Client").list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "GetAll error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return clients;
    }

    public void deleteClient(Client client) throws SQLException {
        Session session = null;
        try {
            session = Hibernate.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Delete error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Collection getClientsByTime(Timestamp time_start,Timestamp time_end) throws SQLException {
        Session session = null;
        List clients;
        try {
            session = Hibernate.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            clients = session.createQuery("from Client,Contract where time_start > :time_start and " +
                    "time_end < :time_end and client = clients.id"
            )
                    .setParameter("time_start",time_start)
                    .setParameter("time_end",time_end)
                    .list();
            session.getTransaction().commit();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return clients;
    }

    public Collection getClientsByServant(Servant servant){
        Session session = null;
        List clients;
        try {
            session = Hibernate.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            int servant_id = servant.getId();
            clients = session.createQuery("from contracts,clients,contract_servant where servant=:servant_id " +
                    "and contract=contracts.id and client=clients.id").setParameter("servant_id", servant_id)
                    .list();
            session.getTransaction().commit();

        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return clients;
    }

}