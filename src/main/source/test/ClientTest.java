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

public class ClientTest {

    private ClientDAO clientDAO = Factory.getInstance().getClientDAO();
    @Test
    public void TestClient() throws Exception {

        ServantDAO servantDAO = Factory.getInstance().getServantDAO();
        ServiceDAO serviceDAO = Factory.getInstance().getServiceDAO();

        Collection clients;
        Client client = new Client();
        client.setName("Harold Black");
        clientDAO.addClient(client);
        Assert.assertEquals(clientDAO.getClientById(7).getName(), "Harold Black");

        client = clientDAO.getClientById(6);
        client.setName("Alex Ward");
        clientDAO.updateClient(6, client);
        Assert.assertEquals(clientDAO.getClientById(6).getName(), "Alex Ward");
        client.setName("Carl Ward");
        clientDAO.updateClient(6, client);

        clientDAO.deleteClient(clientDAO.getClientById(7));
        Assert.assertNull(clientDAO.getClientById(7));
        clients = clientDAO.getAllClients();
        Collection<String> clients_names = new HashSet<String>();
        for (Iterator<Client> iterator = clients.iterator(); iterator.hasNext(); )
            clients_names.add(iterator.next().getName());
        //Среди всех клиентов должна быть компания "Pixope"
        Assert.assertTrue(clients_names.contains("Pixope"));

        Date time_start = new Date(116, 4, 15, 13, 5, 40),
                time_end = new Date(116, 8, 20, 6, 9, 49);
        clients = clientDAO.getClientsByTime(time_start, time_end);
        clients_names.clear();
        for (Iterator<Client> iterator = clients.iterator(); iterator.hasNext(); )
            clients_names.add(iterator.next().getName());
        //Среди этих клиентов должен быть "Carl Ward"
        Assert.assertTrue(clients_names.contains("Carl Ward"));

        Servant servant = servantDAO.getServantById(4);
        clients = clientDAO.getClientsByServant(servant);
        clients_names.clear();
        for (Iterator<Client> iterator = clients.iterator(); iterator.hasNext(); )
            clients_names.add(iterator.next().getName());
        //Среди этих клиентов должна быть "Quamba"
        Assert.assertTrue(clients_names.contains("Quamba"));

        Service service = serviceDAO.getServiceById(1);
        clients = clientDAO.getClientsByService(service);
        clients_names.clear();
        for (Iterator<Client> iterator = clients.iterator(); iterator.hasNext(); )
            clients_names.add(iterator.next().getName());
        //Среди этих клиентов должна быть "Quamba"
        Assert.assertTrue(clients_names.contains("Quamba"));
    }

}
