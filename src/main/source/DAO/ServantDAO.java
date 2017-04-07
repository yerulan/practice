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

public class ServantDAO{

    public void addServant(Servant servant) throws SQLException {
        Session session;
        session = Hibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(servant);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public void updateServant(int servant_id, Servant servant) throws SQLException {
        Session session;
        session = Hibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(servant);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public Servant getServantById(int servant_id) throws SQLException {
        Session session;
        Servant servant;
        session = Hibernate.getSessionFactory().openSession();
        servant = session.get(Servant.class, servant_id);
        if (session != null && session.isOpen()) {
            session.close();
        }
        return servant;
    }

    public Collection getAllServants() throws SQLException {
        Session session;
        List servants;
        session = Hibernate.getSessionFactory().openSession();
        servants = session.createQuery("from Servant").list();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return servants;
    }

    public void deleteServant(Servant servant) throws SQLException {
        Session session;
        session = Hibernate.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(servant);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    public Collection getServantsByTime(Date time_start, Date time_end) throws SQLException {
        Session session;
        List servants;
        session = Hibernate.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        servants = session.createQuery("select b from Servant b inner join b.contracts contract " +
                "where contract.start >= :time_start and " +
                "contract.end <= :time_end")
                .setParameter("time_start",time_start)
                .setParameter("time_end",time_end)
                .list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return servants;
    }

    public Collection getServantsByClient(Client client){
        Session session;
        List servants;
        session = Hibernate.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        servants = session.createQuery("select b from Servant b inner join b.contracts contract" +
                " where client=:client ").setParameter("client", client)
                .list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return servants;
    }

    public Collection getServantsByService(Service service){
        Session session;
        List servants;
        session = Hibernate.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        servants = session.createQuery("select b from Servant b inner join b.contracts contract" +
                " where service=:service ").setParameter("service", service)
                .list();
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return servants;
    }

}