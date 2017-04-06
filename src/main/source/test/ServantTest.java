package test;

import DAO.ClientDAO;
import DAO.ServantDAO;
import DAO.ServiceDAO;
import org.testng.Assert;
import org.testng.annotations.Test;
import store.Client;
import store.Servant;
import store.Service;
import util.Factory;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

public class ServantTest {

    private ServantDAO servantDAO = Factory.getInstance().getServantDAO();
    @Test
    public void TestServant() throws Exception {
        //Методы add*, update*, get*ByID,
        //delete*, getAll*s - такие же как и у ClientDAO

        ClientDAO clientDAO = Factory.getInstance().getClientDAO();
        ServiceDAO serviceDAO = Factory.getInstance().getServiceDAO();

        Collection servants;
        Collection<String> servants_names = new HashSet<String>();

        Date time_start = new Date(116, 4, 15, 13, 5, 40),
                time_end = new Date(116, 8, 20, 6, 9, 49);
        servants = servantDAO.getServantsByTime(time_start, time_end);
        for (Iterator<Servant> iterator = servants.iterator(); iterator.hasNext(); )
            servants_names.add(iterator.next().getName());
        //Среди этих служащих должна быть "Jane Jones"
        Assert.assertTrue(servants_names.contains("Jane Jones"));

        Client client = clientDAO.getClientById(3);
        servants = servantDAO.getServantsByClient(client);
        servants_names.clear();
        for (Iterator<Servant> iterator = servants.iterator(); iterator.hasNext(); )
            servants_names.add(iterator.next().getName());
        //Среди этих служащих должен быть "Gregory Nichols"
        Assert.assertTrue(servants_names.contains("Gregory Nichols"));

        Service service = serviceDAO.getServiceById(1);
        servants = servantDAO.getServantsByService(service);
        servants_names.clear();
        for (Iterator<Servant> iterator = servants.iterator(); iterator.hasNext(); )
            servants_names.add(iterator.next().getName());
        //Среди этих клиентов должен быть "Charles Parker"
        Assert.assertTrue(servants_names.contains("Charles Parker"));
    }

}
