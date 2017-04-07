package test;

import DAO.ServiceDAO;
import org.testng.Assert;
import org.testng.annotations.Test;
import store.Service;
import util.Factory;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class ServiceTest {

    private ServiceDAO serviceDAO = Factory.getInstance().getServiceDAO();
    @Test
    public void TestService() throws Exception {


        Collection services;
        Collection<String> services_names = new HashSet<String>();
        Service service = new Service();
        service.setName("Law issues");
        serviceDAO.addService(service);
        Assert.assertEquals(serviceDAO.getServiceById(5).getName(), "Law issues");

        service = serviceDAO.getServiceById(4);
        service.setName("Lawyer service");
        serviceDAO.updateService(4, service);
        Assert.assertEquals(serviceDAO.getServiceById(4).getName(), "Lawyer service");
        service.setName("Inheritance issues");
        serviceDAO.updateService(4, service);

        serviceDAO.deleteService(serviceDAO.getServiceById(5));
        Assert.assertNull(serviceDAO.getServiceById(5));
        services = serviceDAO.getAllServices();
        for (Iterator<Service> iterator = services.iterator(); iterator.hasNext(); )
            services_names.add(iterator.next().getName());
        Assert.assertTrue(services_names.contains("Inheritance issues"));
    }
}
