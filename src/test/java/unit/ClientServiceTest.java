package unit;

import com.tsystems.javaschool.dao.ClientDAO;
import com.tsystems.javaschool.dto.ClientDTO;
import com.tsystems.javaschool.entity.Address;
import com.tsystems.javaschool.entity.Client;
import com.tsystems.javaschool.error.WrongParameterException;
import com.tsystems.javaschool.service.impl.ClientServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.tsystems.javaschool.entity.Role.USER;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClientServiceTest {
    private final Address address = new Address(2, "", "", "", "", "", "");
    private final Client client = new Client("Ivan", "Ivanov", "123", "Ivan@ivan.ru", "51465", USER, address, null);
    private final ClientDTO clientDTO = new ClientDTO("Ivan", "Ivanov", "Ivan@ivan.ru", "51465", "123", "123", "", "", "", "", "", "", null);

    @Mock
    private ClientDAO clientDAO;
    @InjectMocks
    private ClientServiceImpl clientService;

    @Before
    public void setUp() {
        when(clientDAO.findByUserName("Ivan@ivan.ru")).thenReturn(client);
    }

    @Test
    public void emailExistTrue() {
        assertTrue(clientService.emailExist("Ivan@ivan.ru"));
    }

    @Test
    public void emailExistFalse() {
        assertFalse(clientService.emailExist("Petr@petr.ru"));
    }

    @Test
    public void newClientDoesntHaveAddress() {
        Client clientWithOutAddress = clientService.createClient(clientDTO);
        assertNull(clientWithOutAddress.getAddress());
    }

    @Test
    public void newClientDoesntHaveParent() {
        Client clientWithOutParent = clientService.createClient(clientDTO);
        assertNull(clientWithOutParent.getClientParent());
    }

    @Test(expected = WrongParameterException.class)
    public void registeringExistingClient() {
        clientService.registerClient(clientDTO);
    }


}
