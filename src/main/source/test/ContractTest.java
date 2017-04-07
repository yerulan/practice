package test;

import DAO.ClientDAO;
import DAO.ContractDAO;
import DAO.ServiceDAO;
import org.testng.Assert;
import org.testng.annotations.Test;
import store.Contract;
import util.Factory;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

public class ContractTest {

    private ContractDAO contractDAO = Factory.getInstance().getContractDAO();
    @Test
    public void TestContract() throws Exception {

        ClientDAO clientDAO = Factory.getInstance().getClientDAO();
        ServiceDAO serviceDAO = Factory.getInstance().getServiceDAO();
        Date time_start = new Date(0,0,0,0,0),time;

        Collection contracts;
        Collection<Date> contracts_dates = new HashSet<Date>();
        Contract contract = new Contract();
        contract.setService(serviceDAO.getServiceById(1));
        contract.setClient(clientDAO.getClientById(1));
        contract.setStart(time_start);
        contractDAO.addContract(contract);
        Assert.assertEquals(contractDAO.getContractById(6).getStart().getTime(), time_start.getTime());

        contract = contractDAO.getContractById(4);
        time = contract.getStart();
        contract.setStart(time_start);
        contractDAO.updateContract(4, contract);
        Assert.assertEquals(contractDAO.getContractById(4).getStart().getTime(), time_start.getTime());
        contract.setStart(time);
        contractDAO.updateContract(4, contract);

        contractDAO.deleteContract(contractDAO.getContractById(6));
        Assert.assertNull(contractDAO.getContractById(6));
        contracts = contractDAO.getAllContracts();
        for (Iterator<Contract> iterator = contracts.iterator(); iterator.hasNext(); )
            contracts_dates.add(iterator.next().getStart());
        Assert.assertTrue(contracts_dates.contains(time));
    }
}
