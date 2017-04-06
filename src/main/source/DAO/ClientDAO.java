package DAO;

import org.hibernate.Session;
import store.Client;
import store.Servant;
import store.Service;
import util.Hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ClientDAO{

    public void addClient(Client client) throws SQLException {
        Session session = null;
        session = Hibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void updateClient(int client_id, Client client) throws SQLException {
        Session session = null;
        session = Hibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(client);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public Client getClientById(int client_id) throws SQLException {
        Session session = null;
        Client client = null;
        session = Hibernate.getSessionFactory().openSession();
        client = session.get(Client.class, client_id);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return client;
    }

    public Collection getAllClients() throws SQLException {
        Session session = null;
        List clients;
        session = Hibernate.getSessionFactory().openSession();
        clients = session.createQuery("from Client").list();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return clients;
    }

    public void deleteClient(Client client) throws SQLException {
        Session session = null;
        session = Hibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(client);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public Collection getClientsByTime(Date time_start, Date time_end) throws SQLException {
        Session session = null;
        List clients;
        session = Hibernate.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        clients = session.createQuery("select b from Client b inner join b.contracts contract " +
                "where contract.start >= :time_start and " +
                "contract.end <= :time_end")
                .setParameter("time_start",time_start)
                .setParameter("time_end",time_end)
                .list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return clients;
    }

    public Collection getClientsByServant(Servant servant){
        Session session = null;
        List clients;
        session = Hibernate.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        clients = session.createQuery("select b from Client b inner join b.contracts contract inner join contract.servants" +
                " servant where servant=:servant ").setParameter("servant", servant)
                .list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return clients;
    }

    public Collection getClientsByService(Service service){
        Session session = null;
        List clients;
        session = Hibernate.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        clients = session.createQuery("select b from Client b inner join b.contracts contract" +
                " where service=:service ").setParameter("service", service)
                .list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return clients;
    }

}