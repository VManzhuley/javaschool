package integrational;

import com.tsystems.javaschool.dao.AddressDAO;
import com.tsystems.javaschool.dao.ClientDAO;
import com.tsystems.javaschool.entity.Client;
import com.tsystems.javaschool.service.impl.ClientServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClientServiceTest {

    @MockBean
    private ClientDAO clientDAO;
    @MockBean
    private AddressDAO addressDAO;
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClientServiceImpl clientService;

    private Client client;

    @Before
    public void setUp() {
        client = new Client("Ivan", "Ivan", "123", "Ivan@ivan.ru", "+79441", null, null, null);
    }

    @Test
    public void emailExist() {
        when(clientDAO.findByUserName("Ivan@ivan.ru")).thenReturn(client);

        assertTrue(clientService.emailExist("Ivan@ivan.ru"));
    }
}
